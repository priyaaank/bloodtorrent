package com.b;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@CalatravaPage(name = "donationRequestListing")
public class DonationRequestListingActivity extends RegisteredActivity {

  private static final String TAG = DonationRequestListingActivity.class.getCanonicalName();
  private static final String MAP_FRAGMENT = "map_fragment";
  private static final String DONATIONS = "donations";
  private static final int VIEW_PAGER_ID = 66;

  private DonationsMapFragment mapFragment;
  private ViewPager viewPagerList;
  private DonationViewPagerAdapter viewPagerAdapter;
  private List<DonationsUpdateObserver> observers = new ArrayList<DonationsUpdateObserver>();

  @Override
  protected void onCreate(Bundle availableData) {
    super.onCreate(availableData);
    setContentView(R.layout.donation_request_listing);

    attachMapFragment();
    attachViewPagerFragment();
  }

  private void attachViewPagerFragment() {
    FrameLayout pagerContainer = (FrameLayout) this.findViewById(R.id.listing_container);
    viewPagerList = new ViewPager(this);
    viewPagerList.setId(VIEW_PAGER_ID);
    viewPagerList.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    viewPagerAdapter = new DonationViewPagerAdapter(this.getSupportFragmentManager());
    viewPagerList.setAdapter(viewPagerAdapter);
    pagerContainer.addView(viewPagerList);
  }

  @Override
  protected String getPageName() {
    return "donationRequestListing";
  }

  @Override
  public String getFieldValue(String field) {
    return null;
  }

  @Override
  public void render(String json) {
    try {
      JSONObject dataObject = new JSONObject(json);
      String key = String.valueOf(dataObject.keys().next());
      if(DONATIONS.equalsIgnoreCase(key)) {
        notifySubscribersOfNewListing(dataObject);
      }
    } catch (JSONException e) {
      Log.e(TAG, e.getMessage());
    }
  }

  public void registerObserver(DonationsUpdateObserver observer) {
    if(!observers.contains(observer)) observers.add(observer);
  }

  public void deregisterObserver(DonationsUpdateObserver observer) {
    if(observers.contains(observer)) observers.remove(observer);
  }

  private void notifySubscribersOfNewListing(JSONObject jsonObjectWithDonations) {
    final List<Donation> donationList = donationsListFromJsonObject(jsonObjectWithDonations);

    if(donationList.size() == 0) return;
    for(final DonationsUpdateObserver observer : observers) {
      this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          observer.updatedDonationsList(donationList);
        }
      });
    }

    this.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        viewPagerAdapter.updateList(donationList);
      }
    });
  }

  private List<Donation> donationsListFromJsonObject(JSONObject jsonObjectWithDonations) {
    List donationList = new ArrayList<Donation>();
    try {
      JSONArray donations = jsonObjectWithDonations.getJSONArray(DONATIONS);
      for (int index = 0; index < donations.length(); index++) {
        donationList.add(Donation.fromJsonObject(donations.getJSONObject(index)));
      }
    } catch (JSONException e) {
      Log.e(TAG, e.getMessage());
    }
    return donationList;
  }

  private void attachMapFragment() {
    mapFragment = (DonationsMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_container);
    if(mapFragment == null)
    {
      mapFragment = DonationsMapFragment.newInstance();
      this.getSupportFragmentManager().beginTransaction().add(R.id.map_container, mapFragment, MAP_FRAGMENT).commit();
    }
  }

}
