package com.b;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import net.simonvt.numberpicker.NumberPicker;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CalatravaPage(name = "userSetup")
public class UserSetupActivity extends RegisteredActivity {

  final static List<String> bloodGroupList = new ArrayList<String>();
  final static String TAG = UserSetupActivity.class.getCanonicalName();

  static {
    bloodGroupList.add("OPositive");
    bloodGroupList.add("APositive");
    bloodGroupList.add("BPositive");
    bloodGroupList.add("ABPositive");
    bloodGroupList.add("ONegative");
    bloodGroupList.add("ANegative");
    bloodGroupList.add("BNegative");
    bloodGroupList.add("ABNegative");
  }

  public static final String INIT_VALUES = "initValues";

  final Map<String, Integer> fieldNameToIdMapping = new HashMap<String, Integer>();

  @Override
  public void onCreate(Bundle savedInstance)
  {
    super.onCreate(savedInstance);
    setContentView(R.layout.user_settings);
    initalizeNumberPicker();
    initializeBloodGroupSpinner();
  }

  private void initializeBloodGroupSpinner() {
    Spinner bloodGroupSpinner = (Spinner) this.findViewById(R.id.user_blood_group_value);
    ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bloodGroupList);
    bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    bloodGroupSpinner.setAdapter(bloodGroupAdapter);
  }

  private void initalizeNumberPicker() {
    NumberPicker numberPicker = (NumberPicker)this.findViewById(R.id.user_radius_value);
    numberPicker.setMinValue(1);
    numberPicker.setMaxValue(100);
  }

  public void savePreferences(View saveButton)
  {
    this.triggerEvent("saveUserPreferences", new String[]{});
  }

  @Override
  protected String getPageName() {
    return "userSetup";
  }

  @Override
  public String getFieldValue(String field) {
    if("userName".equalsIgnoreCase(field))
    {
      return ((EditText)this.findViewById(R.id.user_name_value)).getText().toString();
    } else if("bloodGroup".equalsIgnoreCase(field))
    {
      return bloodGroupList.get(((Spinner)this.findViewById(R.id.user_blood_group_value)).getSelectedItemPosition());
    } else if("notificationRadius".equalsIgnoreCase(field))
    {
      return Integer.toString(((NumberPicker)this.findViewById(R.id.user_radius_value)).getValue());
    }

    return null;
  }

  @Override
  public void render(String json) {
    try {
      JSONObject dataObject = new JSONObject(json);
      String key = String.valueOf(dataObject.keys().next());
      if(INIT_VALUES.equalsIgnoreCase(key)) {
        updateViewFromJsonObject(dataObject);
      }
    } catch (JSONException e) {
      Log.e(TAG, e.getMessage());
    }
  }

  private void updateViewFromJsonObject(JSONObject dataObject) throws JSONException {
    JSONObject jsonObject = dataObject.getJSONObject(INIT_VALUES);
    String userName = jsonObject.getString("userName");
    String radius = jsonObject.getString("notificationRadius");

    ((EditText)this.findViewById(R.id.user_name_value)).setText(userName);
    ((NumberPicker)this.findViewById(R.id.user_radius_value)).setValue(Integer.valueOf(radius));
  }
}
