package com.b;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DonationViewPagerAdapter extends FragmentStatePagerAdapter {

  private List<Donation> donationList;

  public DonationViewPagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
    donationList = new ArrayList<Donation>();
  }

  @Override
  public Fragment getItem(int i) {
    return DonationViewFragment.newInstance(donationList.get(i));
  }

  @Override
  public int getCount() {
    return donationList.size();
  }

  @Override
  public int getItemPosition(Object object) {
    return FragmentStatePagerAdapter.POSITION_NONE;
  }

  public void updateList(List<Donation> donationList) {
    this.donationList.clear();
    for(Donation donation : donationList) {
      donationList.add(donation);
    }
    this.notifyDataSetChanged();
  }

  private static class DonationViewFragment extends Fragment {

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
      if(savedInstanceState != null) {
        bloodGroupWithUnits = savedInstanceState.getString(BLOOD_GROUP_WITH_UNITS);
        distanceValue = "500m";
        requestorValue = savedInstanceState.getString(REQUESTOR);
        contactDetailsValue = savedInstanceState.getString(CONTACT_DETAILS);
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

}
