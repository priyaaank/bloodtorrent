package com.calatrava.bridge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.b.R;

public abstract class RegisteredActivity extends FragmentActivity {
  protected static final int DEFAULT_ANIMATION = 0;
  private String TAG = RegisteredActivity.class.getSimpleName();
  private RhinoService rhino;

  private RequestLoader spinner = new RequestLoader(this);
  protected PageStateManager pageStateManager;

  private BroadcastReceiver receiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      Log.d(TAG, "Received broadcast");
      if (intent.getAction().endsWith("start")) {
        if(showLoaderDuringNetworkCalls()) spinner.onLoadingStart();
      } else if (intent.getAction().endsWith("finish")) {
        if(showLoaderDuringNetworkCalls())spinner.onLoadingFinish();
      } else if (intent.getAction().equals("com.calatrava.command")) {
        PluginRegistry.sharedRegistry().runCommand(intent, RegisteredActivity.this);
      }
    }
  };

  protected boolean showLoaderDuringNetworkCalls() {
    return true;
  }

  @Override
  protected void onCreate(Bundle availableData) {
    super.onCreate(availableData);
    initializePageStateManager();
    rhino = ((CalatravaApplication)getApplication()).getRhino();
    pageStateManager.onCreateProcessing();
  }

  protected void initializePageStateManager() {
    pageStateManager = new NativePageStateManager(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    registerReceiver(receiver, new IntentFilter("com.calatrava.ajax.start"));
    registerReceiver(receiver, new IntentFilter("com.calatrava.ajax.finish"));
    registerReceiver(receiver, new IntentFilter("com.calatrava.command"));
    pageStateManager.onResumeProcessing();
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
    pageStateManager.onPauseProcessing();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    pageStateManager.onDestroyProcessing();
  }

  public void triggerEvent(String event, String... extraArgs) {
    PageRegistry.sharedRegistry().triggerEvent(getPageName(), event, extraArgs);
  }

  public void invokeWidgetCallback(String...args) {
    rhino.callJsFunction("calatrava.inbound.invokeCallback", args);
  }

  public void pageOnScreen() {
    PageRegistry.sharedRegistry().pageOnscreen(getPageName());
  }

  public void pageOffScreen() {
    PageRegistry.sharedRegistry().pageOffscreen(getPageName());
  }

  public void registerPage() {
    PageRegistry.sharedRegistry().registerPage(getPageName(), this);
  }

  public void unRegisterPage() {
    PageRegistry.sharedRegistry().unregisterPage(getPageName());
  }

  protected abstract String getPageName();

  public abstract String getFieldValue(String field);

  public abstract void render(final String json);

  public RhinoService getRhino()
  {
    return rhino;
  }

  protected int exitAnimation() {
    return R.anim.push_right_out;
  }

  protected int entryAnimation() {
    return R.anim.push_right_in;
  }

  public void applyAnimations() {
    int enterAnim = entryAnimation();
    int exitAnim = exitAnimation();
    if(enterAnim != DEFAULT_ANIMATION && exitAnim != DEFAULT_ANIMATION)
      overridePendingTransition(enterAnim, exitAnim);
  }

}
