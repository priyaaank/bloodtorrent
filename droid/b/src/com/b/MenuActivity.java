package com.b;

import android.os.Bundle;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;

@CalatravaPage(name = "menu")
public class MenuActivity extends RegisteredActivity {

  @Override
  public void onCreate(Bundle savedInstance)
  {
    super.onCreate(savedInstance);
    setContentView(R.layout.menu);
  }

  @Override
  protected String getPageName() {
    return "menu";
  }

  @Override
  public String getFieldValue(String field) {
    return null;
  }

  @Override
  public void render(String json) {

  }
}
