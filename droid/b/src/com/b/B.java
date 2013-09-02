package com.b;

import com.calatrava.bridge.CalatravaApplication;

public class B extends CalatravaApplication
{
  @Override
  public void onCreate()
  {
    // Call this to start Calatrava
    bootCalatrava("com.b");
  }
}
