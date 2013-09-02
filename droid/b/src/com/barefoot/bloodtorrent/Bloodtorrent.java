package com.barefoot.bloodtorrent;

import com.calatrava.bridge.CalatravaApplication;

public class Bloodtorrent extends CalatravaApplication
{

  public static final String APP_NAME = "com.barefoot.bloodtorrent";

  @Override
  public void onCreate()
  {
    // Call this to start Calatrava
    bootCalatrava(APP_NAME);
  }
}
