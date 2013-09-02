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

@CalatravaPage(name = "userSetup")
public class UserSetupActivity extends RegisteredActivity {

  private final static Map<String, String> bloodGroupList = new LinkedHashMap<String, String>();
  private static List<String> bloodGroupListKeys;
  final static String TAG = UserSetupActivity.class.getCanonicalName();

  static {
    bloodGroupList.put("apositive", "A Positive");
    bloodGroupList.put("bpositive", "B Positive");
    bloodGroupList.put("abpositive", "AB Positive");
    bloodGroupList.put("opositive", "O Positive");
    bloodGroupList.put("anegative", "A Negative");
    bloodGroupList.put("bnegative", "B Negative");
    bloodGroupList.put("abnegative", "AB Negative");
    bloodGroupList.put("onegative", "O Negative");

    bloodGroupListKeys = new ArrayList<String>(bloodGroupList.keySet());
  }

  public static final String TRANSITION_NEXT = "transitionNext";
  public static final String INIT_VALUES = "initValues";

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
    ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>(bloodGroupList.values()));
    bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    bloodGroupSpinner.setAdapter(bloodGroupAdapter);
  }

  private void initalizeNumberPicker() {
    NumberPicker numberPicker = (NumberPicker)this.findViewById(R.id.user_radius_value);
    numberPicker.setMinValue(1);
    numberPicker.setMaxValue(300);
    numberPicker.setValue(40);
    numberPicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
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
    if("userName".equalsIgnoreCase(field)) {
      return ((EditText)this.findViewById(R.id.user_name_value)).getText().toString();
    } else if("bloodGroup".equalsIgnoreCase(field)) {
      return bloodGroupListKeys.get(((Spinner)this.findViewById(R.id.user_blood_group_value)).getSelectedItemPosition());
    } else if("notificationRadius".equalsIgnoreCase(field)) {
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
      } else if(TRANSITION_NEXT.equalsIgnoreCase(key)) {
        this.finish();
      }
    } catch (JSONException e) {
      Log.e(TAG, e.getMessage());
    }
  }

  private void updateViewFromJsonObject(JSONObject dataObject) throws JSONException {
    JSONObject jsonObject = dataObject.getJSONObject(INIT_VALUES);
    String userName = jsonObject.getString("userName");
    String radius = jsonObject.getString("notificationRadius");
    String bloodGroup = jsonObject.getString("bloodGroup");

    if(valueIsValid(userName)) ((EditText)this.findViewById(R.id.user_name_value)).setText(userName);
    if(valueIsValid(radius)) ((NumberPicker)this.findViewById(R.id.user_radius_value)).setValue(Integer.valueOf(radius));
    if(valueIsValid(bloodGroup)) ((Spinner)this.findViewById(R.id.user_blood_group_value)).setSelection(bloodGroupListKeys.indexOf(bloodGroup));
  }

  private boolean valueIsValid(String valueToValidate) {
    return (valueToValidate != null && valueToValidate.trim().length() > 0 && !valueToValidate.equalsIgnoreCase("null"));
  }
}
