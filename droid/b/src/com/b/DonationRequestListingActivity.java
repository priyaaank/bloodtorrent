package com.b;

import android.os.Bundle;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;

@CalatravaPage(name = "donationRequestListing")
public class DonationRequestListingActivity extends RegisteredActivity {

  private static final String MAP_FRAGMENT = "map_fragment";
  private MapListingFragment mapFragment;

  @Override
  protected void onCreate(Bundle availableData) {
    super.onCreate(availableData);
    setContentView(R.layout.donation_request_listing);

    mapFragment = (MapListingFragment) getSupportFragmentManager().findFragmentById(R.id.map_container);
    if(mapFragment == null)
    {
      mapFragment = new MapListingFragment();
      this.getSupportFragmentManager().beginTransaction().add(R.id.map_container, mapFragment, MAP_FRAGMENT).commit();
    }
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

  }
}
