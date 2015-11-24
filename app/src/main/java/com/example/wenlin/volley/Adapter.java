package com.example.wenlin.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wenlin.volley.beans.Data;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by wenlin on 2015/11/10.
 */
public class Adapter extends BaseAdapter {
    private final List<Data.ResultItem> list;
    private Context context;
    private final LayoutInflater layoutInflater;

    public Adapter(Context context){
        super();
        this.list=new ArrayList<>();
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (this.list == null) ? 0 : this.list.size();
    }

    @Override
    public Data.ResultItem getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.list, null);
        }

        final Data.ResultItem resultItem = this.getItem(position);

        ImageView landscapeImageView = (ImageView) convertView.findViewById(R.id.img);
        TextView Id = (TextView) convertView.findViewById(R.id.id);
        TextView NameText = (TextView) convertView.findViewById(R.id.name);
        TextView Sex = (TextView) convertView.findViewById(R.id.sex);

        Picasso.with(convertView.getContext()).load(resultItem.getImageName()).placeholder(R.drawable.ic_loading).into(landscapeImageView);

        Id.setText("id:"+ resultItem.get_id());
        NameText.setText("名字:"+ resultItem.getName());
        Sex.setText("性別: "+resultItem.getSex());
        convertView.setTag(resultItem);
        return convertView;
    }

    public void clear() {
        this.list.clear();
    }

    public boolean addAll(List<Data.ResultItem> data) {
        return this.list.addAll(data);
    }


}
