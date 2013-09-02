package com.barefoot.bloodtorrent;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Donation {

  private static final String TAG = Donation.class.getCanonicalName();

  public static Map<String, String> bloodGroupMapping = new HashMap<String, String>();

  static {
    bloodGroupMapping.put("apositive", "A+");
    bloodGroupMapping.put("bpositive", "B+");
    bloodGroupMapping.put("abpositive", "AB+");
    bloodGroupMapping.put("opositive", "O+");
    bloodGroupMapping.put("anegative", "A-");
    bloodGroupMapping.put("bnegative", "B-");
    bloodGroupMapping.put("abnegative", "AB-");
    bloodGroupMapping.put("onegative", "O-");
  }

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

  public String getBloodGroup() {
    return bloodGroup;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public long getUnits() {
    return quantity;
  }
}
