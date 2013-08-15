package com.b;

import android.os.Bundle;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;

@CalatravaPage(name = "newDonationRequest")
public class NewDonationRequestActivity extends RegisteredActivity {

  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    setContentView(R.layout.new_donation_request);
  }

  @Override
  protected String getPageName() {
    return "newDonationRequest";
  }

  @Override
  public String getFieldValue(String field) {
    return null;
  }

  @Override
  public void render(String json) {
  }
}
