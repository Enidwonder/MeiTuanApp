package com.example.lenovo.sqlite_practice;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo on 2017/6/4.
 */

public class ListViewAdapter extends ArrayAdapter<Restaurant> {

    private int resourceId;

    public ListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    class ViewHolder{
        TextView name;
        TextView address;
        TextView introduction;
        ImageView resPhoto;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Restaurant restaurant = getItem(position);
        View view;
        ViewHolder holder;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder = new ViewHolder();
            holder.name = (TextView)view.findViewById(R.id.restaurant_name);
            holder.introduction = (TextView)view.findViewById(R.id.introduction);
            holder.address = (TextView)view.findViewById(R.id.address);
            holder.resPhoto = (ImageView)view.findViewById(R.id.resPhoto);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        holder.name.setText(restaurant.getName());
        holder.introduction.setText(restaurant.getIntroduction());
        holder.address.setText(restaurant.getAddress());
        if(restaurant.getPhotoPath() == null){
            Glide.with(getContext()).load(android.R.mipmap.sym_def_app_icon).into(holder.resPhoto);
        }else{
            Glide.with(getContext()).load(restaurant.getPhotoPath()).into(holder.resPhoto);
        }

        return view;


    }
}
