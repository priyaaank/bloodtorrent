package com.barefoot.bloodtorrent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;

public class DonationViewFragment extends Fragment {

  public static final String BLOOD_GROUP = "BloodGroupWithUnits";
  public static final String REQUESTOR = "Requestor";
  public static final String CONTACT_DETAILS = "ContactDetails";
  private static final String BLOOD_GROUP_UNITS = "BloodGroupUnits";
  private static final String LATITUDE = "Latitude";
  private static final String LONGITUDE = "Longitude";

  private long units;
  private String bloodGroupValue;
  private String requestorValue;
  private String contactDetailsValue;
  private double latitude;
  private double longitude;

  public static DonationViewFragment newInstance(Donation donation) {
    DonationViewFragment donationViewFragment = new DonationViewFragment();
    Bundle arguments = new Bundle();
    arguments.putString(CONTACT_DETAILS, donation.getContactDetails());
    arguments.putString(BLOOD_GROUP, donation.getBloodGroup());
    arguments.putString(REQUESTOR, donation.getRequestor());
    arguments.putLong(BLOOD_GROUP_UNITS, donation.getUnits());
    arguments.putDouble(LATITUDE, donation.getLatitude());
    arguments.putDouble(LONGITUDE, donation.getLongitude());

    donationViewFragment.setArguments(arguments);
    return donationViewFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle arguments = getArguments();
    if(arguments != null) {
      units = arguments.getLong(BLOOD_GROUP_UNITS);
      bloodGroupValue = arguments.getString(BLOOD_GROUP);
      requestorValue = arguments.getString(REQUESTOR);
      contactDetailsValue = arguments.getString(CONTACT_DETAILS);
      latitude = arguments.getDouble(LATITUDE);
      longitude = arguments.getDouble(LONGITUDE);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.donation_list_row, container, false);
    ((TextView)view.findViewById(R.id.units_of_blood_value)).setText(Long.toString(units));
    ((TextView)view.findViewById(R.id.blood_group_label)).setText(Donation.bloodGroupMapping.get(bloodGroupValue));
    ((TextView)view.findViewById(R.id.requestor_value)).setText(requestorValue);
    ((TextView)view.findViewById(R.id.contact_details_value)).setText(contactDetailsValue);
    view.findViewById(R.id.navigation_image).setTag(new LatLng(latitude, longitude));

    return view;
  }

}
