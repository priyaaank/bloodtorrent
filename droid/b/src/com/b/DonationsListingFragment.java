package com.b;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import java.util.List;

public class DonationsListingFragment extends ListFragment implements DonationsUpdateObserver {

  public static DonationsListingFragment newInstance() {
    return new DonationsListingFragment();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializeList();
  }

  @Override
  public void onResume() {
    super.onResume();
    ((DonationRequestListingActivity)this.getActivity()).registerObserver(this);
  }

  @Override
  public void onPause() {
    super.onPause();
    ((DonationRequestListingActivity)this.getActivity()).deregisterObserver(this);
  }

  @Override
  public void updatedDonationsList(List<Donation> donations) {

  }

  private void initializeList() {

  }
}
