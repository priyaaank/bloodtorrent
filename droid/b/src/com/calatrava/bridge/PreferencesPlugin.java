package com.calatrava.bridge;

import android.content.Context;
import android.content.Intent;
import com.calatrava.CalatravaPlugin;

import java.util.Map;

@CalatravaPlugin(name = "preferences")
public class PreferencesPlugin implements RegisteredPlugin {

  private final String TAG = this.getClass().getCanonicalName();

  private Context context;
  private String okCallbackHandle;
  private PluginRegistry pluginRegistry;

  @Override
  public void setContext(PluginRegistry registry, Context context) {
    this.pluginRegistry = registry;
    this.context = context;
    registry.installCommand("storage", new PluginCommand() {

      @Override
      public void execute(Intent action, RegisteredActivity frontmost) {
        String methodName = action.getExtras().getString("method");
        if(methodName == null || methodName.trim().length() == 0) return;

        if("retrieve".equalsIgnoreCase(methodName))
        {
          callback("");
        } else if("add".equalsIgnoreCase(methodName))
        {
          //Store the value
        } else {
          //Do nothing
        }
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
