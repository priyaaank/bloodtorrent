package com.b;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@CalatravaPage(name = "donationRequestListing")
public class DonationRequestListingActivity extends RegisteredActivity implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

  private static final String TAG = DonationRequestListingActivity.class.getCanonicalName();
  private static final String MAP_FRAGMENT = "map_fragment";
  private static final String DONATIONS = "donations";
  private static final int VIEW_PAGER_ID = 66;

  private DonationsMapFragment mapFragment;
  private ViewPager viewPagerList;
  private LinearLayout noDonationsView;
  private DonationViewPagerAdapter viewPagerAdapter;
  private List<DonationsUpdateObserver> observers = new ArrayList<DonationsUpdateObserver>();
  private LocationClient locationClient;

  @Override
  protected void onCreate(Bundle availableData) {
    super.onCreate(availableData);
    setContentView(R.layout.donation_request_listing);

    attachMapFragment();
    attachViewPagerFragment();
    attachNoDonationsView();
    attachSelfAsLocationListener();
  }

  private void attachNoDonationsView() {
    noDonationsView = (LinearLayout)getLayoutInflater().inflate(R.layout.no_donations_view,null,false);
    FrameLayout pagerContainer = (FrameLayout) this.findViewById(R.id.listing_container);
    pagerContainer.addView(noDonationsView);
  }

  private void attachViewPagerFragment() {
    FrameLayout pagerContainer = (FrameLayout) this.findViewById(R.id.listing_container);
    viewPagerList = new ViewPager(this);
    viewPagerList.setId(VIEW_PAGER_ID);
    viewPagerList.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    viewPagerList.setVisibility(View.GONE);
    viewPagerAdapter = new DonationViewPagerAdapter(this.getSupportFragmentManager());
    viewPagerList.setAdapter(viewPagerAdapter);
    pagerContainer.addView(viewPagerList);
    attachPageChangeListenerToViewPager();
  }

  private void attachPageChangeListenerToViewPager() {
    viewPagerList.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int i, float v, int i2) {
        //Do Nothing
      }

      @Override
      public void onPageSelected(int i) {
        Donation donation = viewPagerAdapter.viewAt(i);
        updateMapForDonation(donation);
      }

      @Override
      public void onPageScrollStateChanged(int i) {
        //Do Nothing
      }
    });
  }

  private void updateMapForDonation(Donation donation) {
    if(donation != null) mapFragment.centerMapTo(donation.getLatitude(), donation.getLongitude());
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

  public void Navigate(View navigateButton) {
    //do nothing
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
        boolean isDonationListEmpty = (donationList == null || donationList.isEmpty());
        manageListingState(isDonationListEmpty);
        if(isDonationListEmpty) updateMapForDonation(donationList.get(0));
        viewPagerAdapter.updateList(donationList);
      }
    });
  }

  private void manageListingState(boolean isDonationListEmpty) {
    int noDonationsViewState = isDonationListEmpty ? View.VISIBLE : View.GONE;
    int pagerViewState = isDonationListEmpty ? View.GONE : View.VISIBLE;
    viewPagerList.setVisibility(pagerViewState);
    noDonationsView.setVisibility(noDonationsViewState);
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

  private void attachSelfAsLocationListener() {
    final int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (result != ConnectionResult.SUCCESS) {
      Toast.makeText(this, "Your location is not available on this phone", Toast.LENGTH_LONG).show();
      return;
    }
    locationClient = new LocationClient(this, this, this);
  }

  @Override
  public void onConnected(Bundle bundle) {
    LocationRequest locationRequest = new LocationRequest ();
    locationRequest.setInterval(60000);
    locationRequest.setSmallestDisplacement(50000);
    locationClient.requestLocationUpdates (locationRequest, this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    locationClient.connect();
  }

  @Override
  protected void onPause() {
    super.onPause();
    locationClient.removeLocationUpdates(this);
    locationClient.disconnect();
  }

  @Override
  public void onDisconnected() {
    Log.d(TAG, "Google play services connection for location disconnected");
    locationClient.removeLocationUpdates(this);
  }

  @Override
  public void onConnectionFailed(ConnectionResult connectionResult) {
    Log.d(TAG, "Google play services connection for location failed");
  }

  @Override
  public void onLocationChanged(Location location) {
    Log.d(TAG, "location=" + location.toString());
    this.triggerEvent("refreshDonations", new String[]{});
  }

  @Override
  protected boolean showLoaderDuringNetworkCalls() {
    return false;
  }
}
