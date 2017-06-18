package com.example.saroj.layout;

/**
 * Created by shudeepslove on 8/13/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Buddy on 6/19/2016.
 */
public class SolventRecyclerViewAdapter extends RecyclerView.Adapter<SolventViewHolder> {

    private List<ItemObjects> itemList;

    public SolventRecyclerViewAdapter(Context context, List<ItemObjects> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    private Context context;

    @Override
    public SolventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_solvent_view, parent, false);
        SolventViewHolder rcv = new SolventViewHolder(layoutView);
        return rcv;


    }

    @Override
    public void onBindViewHolder(SolventViewHolder holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
        holder.countryName1.setText(itemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
