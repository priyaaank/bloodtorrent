package com.b;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DonationViewFragment extends Fragment {

  public static final String BLOOD_GROUP_WITH_UNITS = "BloodGroupWithUnits";
  public static final String REQUESTOR = "Requestor";
  public static final String CONTACT_DETAILS = "ContactDetails";

  private String bloodGroupWithUnits;
  private String distanceValue;
  private String requestorValue;
  private String contactDetailsValue;

  public static DonationViewFragment newInstance(Donation donation) {
    DonationViewFragment donationViewFragment = new DonationViewFragment();
    Bundle arguments = new Bundle();
    arguments.putString(CONTACT_DETAILS, donation.getContactDetails());
    arguments.putString(BLOOD_GROUP_WITH_UNITS, donation.getBloodGroupWithUnits());
    arguments.putString(REQUESTOR, donation.getRequestor());

    donationViewFragment.setArguments(arguments);
    return donationViewFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle arguments = getArguments();
    if(arguments != null) {
      bloodGroupWithUnits = arguments.getString(BLOOD_GROUP_WITH_UNITS);
      distanceValue = "500m";
      requestorValue = arguments.getString(REQUESTOR);
      contactDetailsValue = arguments.getString(CONTACT_DETAILS);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.donation_list_row, container, false);
    ((TextView)view.findViewById(R.id.blood_group_and_units_value)).setText(bloodGroupWithUnits);
    ((TextView)view.findViewById(R.id.distance_value)).setText(distanceValue);
    ((TextView)view.findViewById(R.id.requestor_value)).setText(requestorValue);
    ((TextView)view.findViewById(R.id.contact_details_value)).setText(contactDetailsValue);
    return view;
  }

}
