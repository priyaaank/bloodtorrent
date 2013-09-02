package com.barefoot.bloodtorrent;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

public class LocationMapFragment extends SupportMapFragment {

  public static LocationMapFragment newInstance() {
    return new LocationMapFragment();
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

  private void configureMapVisuals() {
    this.getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
    this.getMap().setMyLocationEnabled(true);
    UiSettings mapUiSettings = this.getMap().getUiSettings();
    mapUiSettings.setCompassEnabled(true);
    mapUiSettings.setMyLocationButtonEnabled(true);
    mapUiSettings.setZoomControlsEnabled(false);
    mapUiSettings.setScrollGesturesEnabled(true);
    mapUiSettings.setTiltGesturesEnabled(true);
  }

  public LatLng getLocationAtCenter() {
    if(this.getMap() == null) return null;
    return this.getMap().getCameraPosition().target;
  }

}
