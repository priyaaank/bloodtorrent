package com.b.plugins;

import android.content.Context;
import android.content.Intent;
import com.calatrava.CalatravaPlugin;
import com.calatrava.bridge.PluginCommand;
import com.calatrava.bridge.PluginRegistry;
import com.calatrava.bridge.RegisteredActivity;
import com.calatrava.bridge.RegisteredPlugin;

import java.util.Map;

@CalatravaPlugin(name = "preferences")
public class PreferencesPlugin implements RegisteredPlugin {

  private final String TAG = this.getClass().getCanonicalName();

  private Context context;
  private String okCallbackHandle;
  private PluginRegistry pluginRegistry;
  private AppPreferences applicationPreferences;

  @Override
  public void setContext(PluginRegistry registry, Context context) {
    this.pluginRegistry = registry;
    this.context = context;
    this.applicationPreferences = new AppPreferences(context.getApplicationContext());
    registry.installCommand("storage", new PluginCommand() {

      @Override
      public void execute(Intent action, RegisteredActivity frontmost) {
        String methodName = action.getExtras().getString("method");
        if(isMethodNameInvalid(methodName)) return;

        String key = action.getExtras().getString("key");
        String value = action.getExtras().getString("value");

        if("retrieve".equalsIgnoreCase(methodName))
        {
          callback(applicationPreferences.retrieve(key));
        } else if("add".equalsIgnoreCase(methodName))
        {
          applicationPreferences.add(key,value);
        }
      }

      private boolean isMethodNameInvalid(String methodName) {
        return methodName == null || methodName.trim().length() == 0;
      }

      private void callback(String storedValue) {
        PreferencesPlugin.this.pluginRegistry.invokeCallback(okCallbackHandle, storedValue);
      }
    });
  }

  @Override
  public void call(String method, Map<String, Object> args) {
    okCallbackHandle = (String) args.get("okHandler");
    String key = (String) ((Map) args.get("message")).get("key");
    String value = (String) ((Map) args.get("message")).get("value");
    context.sendBroadcast(pluginRegistry.pluginCommand("storage")
        .putExtra("method", method)
        .putExtra("key", key)
        .putExtra("value", value));
  }
}
