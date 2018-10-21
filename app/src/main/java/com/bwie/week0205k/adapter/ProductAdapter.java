package com.bwie.week0205k.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.week0205k.R;
import com.bwie.week0205k.bean.Product;

import java.util.List;

/**
 * Created by eric on 2018/10/16.
 */

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product.DataBean> list;

    public ProductAdapter(Context context, List<Product.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_product, null);
            holder.img = convertView.findViewById(R.id.img_product);
            holder.txtTitle = convertView.findViewById(R.id.txt_product);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product.DataBean.ListBean bean = list.get(position).getList().get(0);
        String imgs = bean.getImages();
        String[] strings = imgs.split("\\|");

        Glide.with(context).load(strings[0]).into(holder.img);
        holder.txtTitle.setText(bean.getTitle());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView txtTitle;
    }
}
