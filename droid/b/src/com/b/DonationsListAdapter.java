package com.b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DonationsListAdapter extends ArrayAdapter<Donation> {

  private Context context;
  private int resourceLayoutId;

  public DonationsListAdapter(Context context, int resourceId) {
    super(context, resourceId);
    this.context = context;
    this.resourceLayoutId = resourceId;
  }

  public void updateData(List<Donation> donations) {
    this.clear();

    for(Donation donation : donations) {
      this.add(donation);
    }

    this.setNotifyOnChange(true);
  }

  @Override
  public View getView (int position, View convertView, ViewGroup parent)
  {
    View row = convertView;
    DonationHolder holder = null;
    Donation currentDonation;

    if (row == null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      row = inflater.inflate(resourceLayoutId, parent, false);
      holder = populateDonationRow(row);
      row.setTag(holder);
    } else {
      holder = (DonationHolder) row.getTag();
    }

    currentDonation = this.getItem(position);
    holder.setPropertyValues(currentDonation);

    return row;
  }

  private DonationHolder populateDonationRow(View row) {
    DonationHolder  holder = new DonationHolder ();
    holder.contactDetails = (TextView)row.findViewById (R.id.contact_details_value);
    holder.bloodGroup = (TextView)row.findViewById (R.id.blood_group_value);
    holder.units = (TextView)row.findViewById (R.id.blood_quantity_value);
    holder.contactDetails = (TextView)row.findViewById(R.id.contact_details_value);
    return holder;
  }

  public class DonationHolder {
    public TextView requestor;
    public TextView bloodGroup;
    public TextView units;
    public TextView contactDetails;

    public void setPropertyValues(Donation currentDonation) {
      this.requestor.setText(currentDonation.getRequestor());
      this.bloodGroup.setText(currentDonation.getBloodGroup());
      this.units.setText(currentDonation.getUnits());
      this.contactDetails.setText(currentDonation.getContactDetails());
    }
  }

}
