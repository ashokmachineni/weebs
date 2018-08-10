package com.example.ashokmachineni.dbupdates;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    Context context;
    ArrayList<RvData> rvDatas;

    public RvAdapter(Context context, ArrayList<RvData> rvDatas) {
        this.context = context;
        this.rvDatas = rvDatas;
    }

    @NonNull
    @Override
    public RvAdapter.RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.RvViewHolder holder, int position) {
        final RvData rvData = rvDatas.get(position);
        holder.itemName.setText(rvData.getName());
        String imgUrl = rvData.getImg();
        Glide.with(context)
                .load(imgUrl)
                .thumbnail(0.5f)
                .into(holder.itemImg);
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,NewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvDatas.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView itemImg;
        LinearLayout llItem;
        public RvViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.article_name);
            itemImg = itemView.findViewById(R.id.item_img);
            llItem = itemView.findViewById(R.id.ll_item);

        }
    }
}
