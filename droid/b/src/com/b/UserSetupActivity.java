package com.b;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import net.simonvt.numberpicker.NumberPicker;

import java.util.ArrayList;
import java.util.List;

@CalatravaPage(name = "userSetup")
public class UserSetupActivity extends RegisteredActivity {

  final List<String> bloodGroupList = new ArrayList<String>();

  @Override
  public void onCreate(Bundle savedInstance)
  {
    super.onCreate(savedInstance);
    setContentView(R.layout.user_settings);
    initalizeNumberPicker();
    initializeBloodGroupSpinner();
  }

  private void initializeBloodGroupSpinner() {
    bloodGroupList.add("OPositive");
    bloodGroupList.add("APositive");
    bloodGroupList.add("BPositive");
    bloodGroupList.add("ABPositive");
    bloodGroupList.add("ONegative");
    bloodGroupList.add("ANegative");
    bloodGroupList.add("BNegative");
    bloodGroupList.add("ABNegative");

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

  @Override
  protected String getPageName() {
    return "userSetup";
  }

  @Override
  public String getFieldValue(String field) {
    return null;
  }

  @Override
  public void render(String json) {

  }
}
