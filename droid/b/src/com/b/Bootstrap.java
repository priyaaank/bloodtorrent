package com.b;

import android.os.Bundle;
import com.calatrava.bridge.CalatravaApplication;
import com.calatrava.bridge.RegisteredActivity;

public class Bootstrap extends RegisteredActivity
{
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    CalatravaApplication app = (CalatravaApplication)getApplication();
    app.provideActivityContext(this);

    // And then start your first feature
    app.launchFlow("bloodtorrent.launcher.launch");
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
}
