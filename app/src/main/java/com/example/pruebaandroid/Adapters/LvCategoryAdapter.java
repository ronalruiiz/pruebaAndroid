package com.example.pruebaandroid.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebaandroid.Models.Category;
import com.example.pruebaandroid.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class LvCategoryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Category> categories;

    public LvCategoryAdapter(Context context, int layout, ArrayList<Category> categories) {
        this.context = context;
        this.layout = layout;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolderPattern
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.txtNameCategory = convertView.findViewById(R.id.txtNameCategory);
            viewHolder.imageCategory = convertView.findViewById(R.id.imageCategory);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String currentName = categories.get(position).name;


        viewHolder.txtNameCategory.setText(currentName);
        String url = categories.get(position).url;
        Picasso.get()
                .load(categories.get(position).url)
                .resize(50, 50)
                .centerCrop()
                .into(viewHolder.imageCategory);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageCategory;
        TextView txtNameCategory;
    }
}
