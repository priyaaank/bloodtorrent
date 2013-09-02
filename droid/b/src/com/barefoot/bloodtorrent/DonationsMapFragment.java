package com.barefoot.bloodtorrent;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class DonationsMapFragment extends SupportMapFragment implements DonationsUpdateObserver {

  private static final float DEFAULT_ZOOM_LEVEL = 15.0f;
  private BitmapDescriptor bitmapImage;

  public static DonationsMapFragment newInstance() {
    return new DonationsMapFragment();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    bitmapImage = BitmapDescriptorFactory.fromResource(R.drawable.blip);
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
    this.getMap().clear();
    if(donations == null) return;
    for(Donation donation : donations) {
      this.getMap().addMarker(markerOptionsFor(donation));
    }
  }

  private MarkerOptions markerOptionsFor(Donation donation) {
    MarkerOptions donationMarker = new MarkerOptions();
    donationMarker.position(new LatLng(donation.getLatitude(), donation.getLongitude()));
    donationMarker.title(donation.getContactDetails());
    donationMarker.icon(bitmapImage);
    return  donationMarker;
  }

  public void centerMapTo(double latitude, double longitude) {
    if(this.getMap() != null) {
      CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), DEFAULT_ZOOM_LEVEL);
      this.getMap().animateCamera(cameraUpdate);
    }
  }

}
