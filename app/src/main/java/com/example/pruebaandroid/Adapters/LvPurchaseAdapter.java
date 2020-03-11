package com.example.pruebaandroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pruebaandroid.Models.Product;
import com.example.pruebaandroid.Models.Purchase;
import com.example.pruebaandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LvPurchaseAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Purchase> products;

    public LvPurchaseAdapter(Context context, int layout, ArrayList<Purchase> categories) {
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
            viewHolder.txtPurchaseName = convertView.findViewById(R.id.txtPurchaseName);
            viewHolder.txtPurchaseCount = convertView.findViewById(R.id.txtPurchaseCount);
            viewHolder.txtPurchasePrice = convertView.findViewById(R.id.txtPurchasePrice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String currentName = products.get(position).product.titulo;
        int currentCount = products.get(position).count;
        double currentPrice = products.get(position).priceTotal;

        if(currentName.isEmpty()){
            viewHolder.txtPurchaseName.setText("");
            viewHolder.txtPurchaseCount.setText("");
            viewHolder.txtPurchasePrice.setText("");
        }else{
            viewHolder.txtPurchaseName.setText("Product: "+currentName);
            viewHolder.txtPurchaseCount.setText("Numero:"+currentCount);
            viewHolder.txtPurchasePrice.setText("Valor: $ "+currentPrice);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView txtPurchaseName;
        TextView txtPurchaseCount;
        TextView txtPurchasePrice;
    }
}
