package com.example.saroj.layout;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

//import com.bumptech.glide.Glide;

/**
 * Created by Saroj on 8/8/2016.
 */
public class CustomSwipeAdapter extends PagerAdapter {

    private int[] image_resources ={R.drawable.burn,R.drawable.chestpain,R.drawable.cpr,R.drawable.electricshock,R.drawable.faint,R.drawable.heartattack,R.drawable.heatstroke};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx){
        this.ctx = ctx;
    }


    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object );
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        //Glide.with(ctx).load("http://192.168.0.105/BloodMapNepal/bloodMapNepal/uploads/dental.jpg").into(imageView);
       // ImageView imageView = (ImageView) item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_resources[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
