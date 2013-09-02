package com.barefoot.bloodtorrent;

import com.barefoot.bloodtorrent.Donation;

import java.util.List;

public interface DonationsUpdateObserver {

  public void updatedDonationsList(List<Donation> donations);

}
