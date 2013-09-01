package com.b;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;

public class GpsManager {

  private Context applicationContext;

  public GpsManager(Context context)
  {
    this.applicationContext = context;
  }

  public void prepareGPS()
  {
    if ( gpsPresent() && dontHave(gpsEnabled()) )
    {
      AlertDialog.Builder dialog = new AlertDialog.Builder (applicationContext);
      dialog.setTitle("Location Manager");
      dialog.setMessage("Would you like to enable GPS?");
      dialog.setPositiveButton ("Yes", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          showGpsSettings();
        }
      });
      dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          //Do nothing
        }
      });
      dialog.create().show();
    }
  }

  private void showGpsSettings()
  {
    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    applicationContext.startActivity (intent);
  }

  private boolean gpsEnabled()
  {
    LocationManager manager = (LocationManager) applicationContext.getSystemService(Context.LOCATION_SERVICE);
    return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }

  private boolean gpsPresent()
  {
    PackageManager packageManager = applicationContext.getPackageManager();
    return packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
  }

  private boolean dontHave(boolean condition)
  {
    return !condition;
  }

}
