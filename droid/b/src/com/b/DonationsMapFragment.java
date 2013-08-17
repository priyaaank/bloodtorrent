package com.b;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

import java.util.List;

public class DonationsMapFragment extends SupportMapFragment implements DonationsUpdateObserver {

  public static DonationsMapFragment newInstance() {
    return new DonationsMapFragment();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializeMap();
  }

  private void initializeMap() {
    if(this.getMap() != null) {
      configureMapVisuals();
    }
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

  private void configureMapVisuals() {
    this.getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
    this.getMap().setMyLocationEnabled(true);
    UiSettings mapUiSettings = this.getMap().getUiSettings();
    mapUiSettings.setCompassEnabled(true);
    mapUiSettings.setMyLocationButtonEnabled(true);
    mapUiSettings.setZoomControlsEnabled(true);
    mapUiSettings.setScrollGesturesEnabled(true);
    mapUiSettings.setTiltGesturesEnabled(true);
  }

  @Override
  public void updatedDonationsList(List<Donation> donations) {

  }
}
