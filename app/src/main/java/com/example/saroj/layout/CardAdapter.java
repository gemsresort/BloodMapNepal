package com.example.saroj.layout;
//import android.content.Context;
//import android.media.Image;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.NetworkImageView;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
//
//    private static final String TAG = null;
//    private ImageLoader imageLoader;
//    private Context context;
//
//    //List of superHeroes
//    List<SuperHeroes> superHeroes;
//
//    public CardAdapter(List<SuperHeroes> superHeroes, Context context){
//        super();
//        //Getting all the superheroes
//        this.superHeroes = superHeroes;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.superheroes_list, parent, false);
//        ViewHolder viewHolder = new ViewHolder(v);
//        return viewHolder;
//    }
//
//
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        SuperHeroes superHero =  superHeroes.get(position);
//
//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        //   Log.i(TAG,""+imageLoader);
//        imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
//        //  holder.imageView.setImageUrl(Config.id+"sau/",imageLoader);
//        //  holder.imageView.setImageUrl(Config.id+"sau/", imageLoader);
//        holder.imageView.setImageUrl(superHero.getImageUrl(), imageLoader);
//        holder.textViewName.setText(superHero.getName());
//        //    holder.textViewRank.setText(String.valueOf(superHero.getRank()));
//        //   holder.textViewRealName.setText(superHero.getRealName());
//        holder.textViewCreatedBy.setText(superHero.getCreatedBy());
//        //  holder.textViewFirstAppearance.setText(superHero.getFirstAppearance());x
//
//        //   String powers = "";
////
////        for(int i = 0; i<superHero.getPowers().size(); i++){
////            powers+= superHero.getPowers().get(i);
////        }
////
////        holder.textViewPowers.setText(powers);
//    }
//
//    @Override
//    public int getItemCount() {
//        return superHeroes.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder{
//        public NetworkImageView imageView;
//        public TextView textViewName;
//        //   public TextView textViewRank;
//        //  public TextView textViewRealName;
//        public TextView textViewCreatedBy;
//        //   public TextView textViewFirstAppearance;
//        //  public TextView  textViewPowers;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
//            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
//            //   textViewRank= (TextView) itemView.findViewById(R.id.textViewRank);
//            //  textViewRealName= (TextView) itemView.findViewById(R.id.textViewRealName);
//            textViewCreatedBy= (TextView) itemView.findViewById(R.id.textViewCreatedBy);
//            //  textViewFirstAppearance= (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
//            //  textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
//        }
//    }
//}

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<SuperHeroes> superHeroes;

    public CardAdapter(List<SuperHeroes> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.superheroes_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        SuperHeroes superHero =  superHeroes.get(position);

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(superHero.getImageUrl(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.imageView.setImageUrl(superHero.getImageUrl(), imageLoader);
        holder.textViewName.setText(superHero.getName());
      //  holder.textViewRank.setText(String.valueOf(superHero.getRank()));
       holder.textViewRealName.setText(superHero.getRealName());
        holder.textViewCreatedBy.setText(superHero.getCreatedBy());
      //  holder.textViewFirstAppearance.setText(superHero.getFirstAppearance());
      //  holder.textViewCreatedBy.setText(superHero.getCreatedBy());
        String powers = "";
//
//        for(int i = 0; i<superHero.getPowers().size(); i++){
//            powers+= superHero.getPowers().get(i);
//        }

//        holder.textViewPowers.setText(powers);
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewRank;
        public TextView textViewRealName;
        public TextView textViewCreatedBy;
        public TextView textViewFirstAppearance;
        public TextView textViewPowers;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            //  textViewRank= (TextView) itemView.findViewById(R.id.textViewRank);
            textViewRealName= (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            //  textViewFirstAppearance= (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            // textViewPowers= (TextView) itemView.findViewById(R.id.textViewPowers);
        }
    }
    }