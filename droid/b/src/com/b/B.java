package com.b;

import android.app.Activity;
import android.os.Bundle;

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
