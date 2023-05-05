package com.gsoft.homework.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gsoft.homework.databinding.VenueItemBinding;
import com.gsoft.homework.models.Venue;

import java.util.List;

public class VenueAdapter extends BaseAdapter {

    private List<Venue> venueList;
    private LayoutInflater layoutInflater;

    public VenueAdapter(Context context, List<Venue> itemList) {
        this.venueList = itemList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return venueList.size();
    }

    @Override
    public Object getItem(int position) {
        return venueList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VenueItemBinding binding;

        if (convertView == null) {
            binding = VenueItemBinding.inflate(layoutInflater, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (VenueItemBinding) convertView.getTag();
        }

        binding.setItem(venueList.get(position));

        return convertView;
    }
}
