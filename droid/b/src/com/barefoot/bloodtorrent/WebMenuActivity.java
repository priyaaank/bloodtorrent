package com.barefoot.bloodtorrent;

import com.calatrava.CalatravaPage;
import com.calatrava.shell.WebViewActivity;

import java.util.Arrays;
import java.util.List;

@CalatravaPage(name="menu")
public class WebMenuActivity extends WebViewActivity {
  @Override
  protected List<String> getEvents() {
    return Arrays.asList(new String[] {"donationRequests","newDonationRequest","settings"});
  }

  @Override
  protected List<String> getFields() {
    return Arrays.asList(new String[] {});
  }

  @Override
  protected String getPageName() {
    return "menu";
  }
}
