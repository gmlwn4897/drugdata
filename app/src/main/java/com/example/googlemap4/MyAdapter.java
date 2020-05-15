package com.example.googlemap4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public MyAdapter(Context context, ArrayList<Item> items){
        this.mList = items;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dutyName.setText(mList.get(position).dutyName);
        holder.dutyAddr.setText(mList.get(position).dutyAddr);
        holder.dutyTel1.setText(mList.get(position).dutyTel1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView dutyName;
        public TextView dutyAddr;
        public TextView dutyTel1;

        public MyViewHolder(View itemView){
            super(itemView);

            dutyName = itemView.findViewById(R.id.drug_dutyName);
            dutyAddr = itemView.findViewById(R.id.drug_dutyAddr);
            dutyTel1 = itemView.findViewById(R.id.drug_dutyTel1);
        }
    }

}
