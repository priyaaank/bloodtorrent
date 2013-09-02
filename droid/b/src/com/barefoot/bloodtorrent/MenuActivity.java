package com.barefoot.bloodtorrent;

import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.calatrava.CalatravaPage;
import com.calatrava.bridge.RegisteredActivity;

@CalatravaPage(name = "menu")
public class MenuActivity extends RegisteredActivity {

  @Override
  public void onCreate(Bundle savedInstance)
  {
    super.onCreate(savedInstance);
    setContentView(R.layout.menu);
    initializeMenuImage();
  }

  private void initializeMenuImage() {
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.menu_image);
    Bitmap circleImage = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    Paint paint = new Paint();
    paint.setShader(shader);

    Canvas canvas = new Canvas(circleImage);

    Paint borderPaint = new Paint();
    borderPaint.setColor(0xff50597b);

    //border
    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, (bitmap.getHeight() / 2), borderPaint);
    //image
    canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, (bitmap.getHeight() / 2)-10, paint);

    ((ImageView)this.findViewById(R.id.menu_image)).setImageBitmap(circleImage);
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
    //Do Nothing
  }

  public void initiateNewRequestFlow(View newRequestButton) {
    this.triggerEvent("newDonationRequest", new String[]{});
  }

  public void initiateRequestListingFlow(View donationsButton) {
    this.triggerEvent("donationRequests", new String[]{});
  }

  public void initiateSettingsFlow(View settingsButton) {
      this.triggerEvent("settings", new String[]{});
  }

  @Override
  protected boolean showLoaderDuringNetworkCalls() {
    return false;
  }
}
