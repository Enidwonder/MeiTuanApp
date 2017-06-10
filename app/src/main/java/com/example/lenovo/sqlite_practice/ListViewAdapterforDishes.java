package com.example.lenovo.sqlite_practice;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/6/4.
 */

public class ListViewAdapterforDishes extends ArrayAdapter<Dish> {

    private int resourceId;
    private AddDishClickListener onClickListener;

    public ListViewAdapterforDishes(@NonNull Context context, @LayoutRes int resource, @NonNull List<Dish> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    class ViewHolder{
        ImageView addButton ;
        ImageView offButton ;
        TextView dishAmount;
        TextView dish_name;
        TextView price;
    }

    public void setOnAddButtonClickListener(AddDishClickListener listener){
        onClickListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View view;
        ViewHolder holder;
        final Dish dish = getItem(position);
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder = new ViewHolder();
            holder.addButton = (ImageView)view.findViewById(R.id.addToOrder);
            holder.offButton = (ImageView)view.findViewById(R.id.offDish);
            holder.dishAmount = (TextView)view.findViewById(R.id.dish_amount);
            holder.dish_name = (TextView)view.findViewById(R.id.dishes_name);
            holder.price = (TextView)view.findViewById(R.id.price);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        holder.dishAmount.setText(String.valueOf(dish.getOrderedAmount()));
        holder.price.setText(String.valueOf(dish.getPrice()));
        holder.dish_name.setText(dish.getDish_name());
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.onClick(view,dish);
                }

            }
        });
        return view;
    }
}
