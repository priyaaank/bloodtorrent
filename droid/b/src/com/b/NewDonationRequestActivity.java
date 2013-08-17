package com.b;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import com.google.android.gms.maps.model.LatLng;
import org.json.JSONException;
import org.json.JSONObject;

@CalatravaPage(name = "newDonationRequest")
public class NewDonationRequestActivity extends RegisteredActivity {

  private static final String UPDATE_LOCATION = "updateLocation";
  private static final String TAG = NewDonationRequestActivity.class.getCanonicalName();

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
    //Do nothing
  }

  public void createNewDonation(View newDonationButton) {
    this.triggerEvent("submitDonationRequest", new String[]{});
  }

  public void captureLocation(View captureLocationButton) {
    this.triggerEvent("showLocationCapturePage", new String[]{});
  }
}
