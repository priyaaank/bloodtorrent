package com.barefoot.bloodtorrent.plugins;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

  private SharedPreferences applicationPreferences;

  public AppPreferences(Context applicationContext) {
    this.applicationPreferences = applicationContext.getSharedPreferences(applicationContext.getPackageName(),Context.MODE_PRIVATE);
  }

  public void add(String key, String value) {
    applicationPreferences.edit().putString(key, value).commit();
  }

  public String retrieve(String key) {
    return applicationPreferences.getString(key, null);
  }

}
