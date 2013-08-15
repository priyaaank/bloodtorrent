package com.b;

import android.os.Bundle;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;

@CalatravaPage(name = "donationRequestListing")
public class DonationRequestListingActivity extends RegisteredActivity {

  @Override
  protected void onCreate(Bundle availableData) {
    super.onCreate(availableData);
    setContentView(R.layout.donation_request_listing);
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
