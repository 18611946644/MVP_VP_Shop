package com.bwie.week0205k.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.week0205k.R;
import com.bwie.week0205k.bean.Category;

import java.util.List;

/**
 * Created by eric on 2018/10/16.
 */

public class CategotyAdapter extends BaseAdapter {
    private Context context;
    private List<Category.DataBean> list;

    public CategotyAdapter(Context context, List<Category.DataBean> list) {
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
            convertView = View.inflate(context, R.layout.item_category, null);
            holder.img = convertView.findViewById(R.id.img_category);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getIcon()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
    }
}
