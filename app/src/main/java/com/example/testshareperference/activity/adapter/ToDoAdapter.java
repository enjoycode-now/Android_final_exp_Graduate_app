package com.example.testshareperference.activity.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testshareperference.R;
import com.example.testshareperference.activity.entity.item_EverSummary;
import com.xuexiang.xui.widget.textview.autofit.AutoFitTextView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
    private List<item_EverSummary> list;
    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_date;
//        TextView textView_content;
        AutoFitTextView textView_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_content = itemView.findViewById(R.id.item_everyday_summmary_textView_content);
            textView_date = itemView.findViewById(R.id.item_everyday_summmary_textView_date);
        }
    }

    public ToDoAdapter(List<item_EverSummary> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_everday_summary,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoAdapter.ViewHolder holder, int position) {
        item_EverSummary item_everSummary = list.get(position);
        Log.d("myinfo", "onBindViewHolder: "+position);
        holder.textView_date.setText(item_everSummary.getDate());
        holder.textView_content.setText(item_everSummary.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
