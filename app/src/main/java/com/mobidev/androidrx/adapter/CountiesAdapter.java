package com.mobidev.androidrx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobidev.androidrx.R;
import com.mobidev.androidrx.model.County;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lawrence on 9/4/16.
 */
public class CountiesAdapter extends RecyclerView.Adapter<CountiesAdapter.ViewHolder> {

    private final Context mContext;
    private final List<County> mCounties = new ArrayList<>();

    public CountiesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setCounties(List<County> countiesList) {
        mCounties.clear();
        mCounties.addAll(countiesList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mColorDisplay.setText(mCounties.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Code: " + mCounties.get(position).code, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCounties.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mColorDisplay;

        public ViewHolder(View view) {
            super(view);
            mColorDisplay = (TextView) view.findViewById(R.id.color_display);
        }
    }
}
