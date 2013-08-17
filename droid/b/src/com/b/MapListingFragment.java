package com.b;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;

public class MapListingFragment  extends SupportMapFragment {

  public static MapListingFragment newInstance() {
    return new MapListingFragment();
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
    mapUiSettings.setZoomControlsEnabled(true);
    mapUiSettings.setScrollGesturesEnabled(true);
    mapUiSettings.setTiltGesturesEnabled(true);
  }
}
