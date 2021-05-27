package com.example.testshareperference.activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
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
    private  int position;
    private List<item_EverSummary> list;
    private Context mContext;

    public  int getContextMenuPosition() {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        //保存子项最外层布局的view
        View itemView;
        TextView textView_date;
        AutoFitTextView textView_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_content = itemView.findViewById(R.id.item_everyday_summmary_textView_content);
            textView_date = itemView.findViewById(R.id.item_everyday_summmary_textView_date);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("记录"+(getContextMenuPosition()+1));
            menu.add(ContextMenu.NONE, 0, ContextMenu.NONE, "查看");
            menu.add(ContextMenu.NONE, 1, ContextMenu.NONE, "删除");
            menu.add(ContextMenu.NONE, 2, ContextMenu.NONE, "修改");
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"长按弹出菜单",Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setContextMenuPosition(holder.getLayoutPosition());
                return false;
            }
        });
    }

    private void setContextMenuPosition(int layoutPosition) {
        this.position = layoutPosition;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public List<item_EverSummary> getList() {
        return list;
    }

    public void setList(List<item_EverSummary> list) {
        this.list = list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }


}
