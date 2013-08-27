package com.b;

import android.os.Bundle;
import com.calatrava.bridge.CalatravaApplication;
import com.calatrava.bridge.RegisteredActivity;

public class Bootstrap extends RegisteredActivity
{

  private CalatravaApplication app;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    app = (CalatravaApplication)getApplication();
    app.provideActivityContext(this);
  }

  @Override
  protected String getPageName() {
    return null;
  }

  @Override
  public String getFieldValue(String field) {
    return null;
  }

  @Override
  public void render(String json) {

  }

  @Override
  protected void onResume() {
    super.onResume();
    // And then start your first feature
    app.launchFlow("bloodtorrent.launcher.launch");

  }

  @Override
  protected void onPause() {
    super.onPause();
    this.finish();
  }
}
