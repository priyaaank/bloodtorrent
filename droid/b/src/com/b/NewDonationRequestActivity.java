package com.b;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    if("bloodGroup".equalsIgnoreCase(field))
    {
      return ((EditText)this.findViewById(R.id.blood_group_value)).getText().toString();
    } else if("quantity".equalsIgnoreCase(field))
    {
      return ((EditText)this.findViewById(R.id.blood_quantity_value)).getText().toString();
    } else if("contactDetails".equalsIgnoreCase(field))
    {
      return ((EditText)this.findViewById(R.id.contact_details_value)).getText().toString();
    }

    return null;
  }

  @Override
  public void render(String json) {
    //Do Nothing
  }

  public void createNewDonation(View newDonationButton) {
    this.triggerEvent("submitDonationRequest", new String[]{});
  }
}
