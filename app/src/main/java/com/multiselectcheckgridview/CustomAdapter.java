package com.multiselectcheckgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ayesha on 15-12-2016.
 */

public class CustomAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater layoutInflater;
    private List<DataModel> mArrayList;

    public CustomAdapter(Context ctx, List<DataModel> mArrayList) {
        this.ctx = ctx;
        this.layoutInflater = LayoutInflater.from(ctx);
        this.mArrayList = mArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
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
            view = layoutInflater.inflate(R.layout.row_item, null);

            holder.mCheckBox = (CheckBox) view.findViewById(R.id.ck_box);
            holder.mTvName = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTvName.setText(mArrayList.get(position).getName());
      /*  if (mArrayList.get(position).isFlag() == true)
            holder.mCheckBox.setChecked(true);*/
        return view;
    }

    public class ViewHolder {
        CheckBox mCheckBox;
        TextView mTvName;

    }
}
