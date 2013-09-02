package com.barefoot.bloodtorrent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import net.simonvt.numberpicker.NumberPicker;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CalatravaPage(name = "newDonationRequest")
public class NewDonationRequestActivity extends RegisteredActivity {

  private static final String TAG = NewDonationRequestActivity.class.getCanonicalName();

  private final static Map<String, String> bloodGroupList = new LinkedHashMap<String, String>();
  private static final String BACK = "back";
  private static List<String> bloodGroupListKeys;

  static {
    bloodGroupList.put("a+", "A Positive");
    bloodGroupList.put("b+", "B Positive");
    bloodGroupList.put("ab+", "AB Positive");
    bloodGroupList.put("o+", "O Positive");
    bloodGroupList.put("a-", "A Negative");
    bloodGroupList.put("b-", "B Negative");
    bloodGroupList.put("ab-", "AB Negative");
    bloodGroupList.put("o-", "O Negative");

    bloodGroupListKeys = new ArrayList<String>(bloodGroupList.keySet());
  }

  @Override
  public void onCreate(Bundle savedInstance) {
    super.onCreate(savedInstance);
    setContentView(R.layout.new_donation_request);
    initializeBloodGroupPicker();
    initializeBloodUnitsSelector();
  }

  private void initializeBloodUnitsSelector() {
    NumberPicker bloodGroupUnitsPicker = (NumberPicker) this.findViewById(R.id.blood_quantity_value);
    bloodGroupUnitsPicker.setMinValue(1);
    bloodGroupUnitsPicker.setMaxValue(20);
    bloodGroupUnitsPicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
  }

  private void initializeBloodGroupPicker() {
    Spinner bloodGroupSpinner = (Spinner) this.findViewById(R.id.blood_group_value);
    ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(bloodGroupList.values()));
    bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    bloodGroupSpinner.setAdapter(bloodGroupAdapter);
  }

  @Override
  protected String getPageName() {
    return "newDonationRequest";
  }

  @Override
  public String getFieldValue(String field) {
    if("bloodGroup".equalsIgnoreCase(field))
    {
      return bloodGroupListKeys.get(((Spinner) this.findViewById(R.id.blood_group_value)).getSelectedItemPosition());
    } else if("quantity".equalsIgnoreCase(field))
    {
      return Integer.toString(((NumberPicker) this.findViewById(R.id.blood_quantity_value)).getValue());
    } else if("contactDetails".equalsIgnoreCase(field))
    {
      return ((EditText)this.findViewById(R.id.contact_details_value)).getText().toString();
    }

    return null;
  }

  @Override
  public void render(String json) {
    try {
      JSONObject dataObject = new JSONObject(json);
      String key = String.valueOf(dataObject.keys().next());
      if(BACK.equalsIgnoreCase(key)) {
        finishAndGoBack();
      }
    } catch (JSONException e) {
      Log.e(TAG, e.getMessage());
    }
  }

  private void finishAndGoBack() {
    this.finish();
  }

  public void createNewDonation(View newDonationButton) {
    this.triggerEvent("submitDonationRequest", new String[]{});
  }

  public void captureLocation(View captureLocationButton) {
    this.triggerEvent("showLocationCapturePage", new String[]{});
  }
}
