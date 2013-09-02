package com.barefoot.bloodtorrent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;
import com.google.android.gms.maps.model.LatLng;

@CalatravaPage(name = "captureLocation")
public class CaptureLocationActivity extends RegisteredActivity {

  private static final String MAP_FRAGMENT = "map_fragment";
  private LocationMapFragment mapFragment;

  @Override
  protected void onCreate(Bundle availableData) {
    super.onCreate(availableData);
    setContentView(R.layout.capture_location);
    attachMapFragment();
  }

  @Override
  protected void onResume() {
    super.onResume();
    Toast.makeText(this, "Zoom in and move donation place to the cross hair on map", Toast.LENGTH_LONG).show();
  }

  @Override
  protected String getPageName() {
    return "captureLocation";
  }

  @Override
  public String getFieldValue(String field) {
    return null;
  }

  @Override
  public void render(String json) {
    //Do Nothing
  }

  public void returnSelectedLocation(View setLocationButton) {
    LatLng locationAtCenter = mapFragment.getLocationAtCenter();
    if(locationAtCenter != null)
      this.triggerEvent("updateLocationForDonation", new String[]{Double.toString(locationAtCenter.latitude),Double.toString(locationAtCenter.longitude)});
    this.finish();
  }

  private void attachMapFragment() {
    mapFragment = (LocationMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_for_location_container);
    if(mapFragment == null)
    {
      mapFragment = LocationMapFragment.newInstance();
      this.getSupportFragmentManager().beginTransaction().add(R.id.map_for_location_container, mapFragment, MAP_FRAGMENT).commit();
    }
  }
}
