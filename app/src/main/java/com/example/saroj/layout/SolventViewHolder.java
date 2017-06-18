package com.example.saroj.layout;

/**
 * Created by shudeepslove on 8/13/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Buddy on 6/19/2016.
 */
public class SolventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView countryName,countryName1;
    public ImageView countryPhoto;

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "+position" + getAdapterPosition(), Toast.LENGTH_SHORT).show();

    }

    public SolventViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView) itemView.findViewById(R.id.first_name);
        countryName1 = (TextView) itemView.findViewById(R.id.address);
        // countryPhoto=(ImageView)itemView.findViewById(R.id.fo);
    }
}
