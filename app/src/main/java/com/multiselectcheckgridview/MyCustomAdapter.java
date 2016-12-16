package com.multiselectcheckgridview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aalishan on 16/12/16.
 */


public class MyCustomAdapter extends BaseAdapter {
    Context context;
    List<DataModel> modelList;
    LayoutInflater inflater;

    public MyCustomAdapter(Context context, List<DataModel> modelList) {
        this.context = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_item, null);
            holder.tvItemName = (TextView) view.findViewById(R.id.tv_name);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (modelList.get(position).getCheckStatus()) {
            //holder.tvItemName.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            holder.tvItemName.setBackgroundResource(R.drawable.button_bg1);

        } else {
            //holder.tvItemName.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.tvItemName.setBackgroundResource(R.drawable.button_bg);
        }
        holder.tvItemName.setText(modelList.get(position).getName());
        return view;
    }

    public class ViewHolder {
        TextView tvItemName;

    }
}





