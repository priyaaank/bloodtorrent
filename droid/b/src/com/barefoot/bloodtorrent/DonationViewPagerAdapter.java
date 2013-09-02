package com.barefoot.bloodtorrent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
      this.donationList.add(donation);
    }
    this.notifyDataSetChanged();
  }

  public Donation viewAt(int index) {
    return donationList.get(index);
  }
}
