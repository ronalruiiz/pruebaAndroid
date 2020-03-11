package com.example.pruebaandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebaandroid.Models.Product;
import com.example.pruebaandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LvProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Product> products;

    public LvProductAdapter(Context context, int layout, ArrayList<Product> categories) {
        this.context = context;
        this.layout = layout;
        this.products = categories;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewHolderPattern
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imageProduct = convertView.findViewById(R.id.imageProduct);
            viewHolder.txtNameProduct = convertView.findViewById(R.id.txtNameProduct);
            viewHolder.txtDescriptionProduct = convertView.findViewById(R.id.txtDescriptionProduct);
            viewHolder.txtPriceProduct = convertView.findViewById(R.id.txtPriceProduct);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String currentName = products.get(position).titulo;
        String currentDescription = products.get(position).contenido;
        String currentPrice = products.get(position).precio;

        if(currentName.isEmpty() && currentDescription.isEmpty() && currentPrice.isEmpty()){
            viewHolder.txtNameProduct.setText("");
            viewHolder.txtDescriptionProduct.setText("");
            viewHolder.txtPriceProduct.setText("");
        }else{
            viewHolder.txtNameProduct.setText(currentName);
            viewHolder.txtDescriptionProduct.setText(currentDescription);
            viewHolder.txtPriceProduct.setText("$ "+currentPrice);
        }

        Picasso.get()
                .load(products.get(position).urlImagen)
                .resize(50, 50)
                .centerCrop()
                .into(viewHolder.imageProduct);


        return convertView;
    }

    static class ViewHolder {
        ImageView imageProduct;
        TextView txtNameProduct;
        TextView txtDescriptionProduct;
        TextView txtPriceProduct;
    }
}
