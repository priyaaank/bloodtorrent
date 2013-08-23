package com.b;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Donation {

  private static final String TAG = Donation.class.getCanonicalName();

  private String id;
  private String bloodGroup;
  private String contactDetails;
  private double latitude;
  private double longitude;
  private long quantity;
  private String requestor;

  public Donation(String id, String bloodGroup, String contactDetails, double latitude, double longitude, long quantity, String requestor) {
    this.id = id;
    this.bloodGroup = bloodGroup;
    this.contactDetails = contactDetails;
    this.latitude = latitude;
    this.longitude = longitude;
    this.quantity = quantity;
    this.requestor = requestor;
  }

  public static Donation fromJsonObject(JSONObject object) {
    if(object == null) return null;
    Donation donation = null;

    try {
      JSONArray location = object.getJSONArray("coordinates");
      float lng = Float.valueOf(location.get(0).toString());
      float lat = Float.valueOf(location.get(1).toString());
      donation = new Donation(object.getString("_id"), object.getString("blood_group"), object.getString("contact_details"),
                              lat, lng, object.getLong("quantity"), object.getString("requestor") );
    } catch (JSONException e) {
      Log.d(TAG, e.getMessage());
    }
    return donation;
  }


  public String getContactDetails() {
    return contactDetails;
  }

  public String getRequestor() {
    return requestor;
  }

  public String getBloodGroupWithUnits() {
    StringBuilder unitWithBloodGroup = new StringBuilder("")
    .append(quantity)
    .append(" units ")
    .append(" of ")
    .append(bloodGroup)
    .append(" blood group");
    return unitWithBloodGroup.toString();
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
