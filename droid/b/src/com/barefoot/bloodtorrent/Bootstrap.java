package com.barefoot.bloodtorrent;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.calatrava.bridge.CalatravaApplication;
import com.calatrava.bridge.RegisteredActivity;

public class Bootstrap extends RegisteredActivity
{

  private CalatravaApplication app;
  private Handler handler;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    app = (CalatravaApplication)getApplication();
    app.provideActivityContext(this);
    handler = new Handler();
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

    ImageView im = (ImageView)this.findViewById(R.id.heart_container);
    Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
    im.startAnimation(pulse);

    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        // And then start your first feature
        app.launchFlow("bloodtorrent.launcher.launch");
      }
    }, 3000);

  }

  @Override
  protected void onPause() {
    super.onPause();
  }

  @Override
  protected int entryAnimation() {
    return DEFAULT_ANIMATION;
  }

  @Override
  protected int exitAnimation() {
    return DEFAULT_ANIMATION;
  }
}
