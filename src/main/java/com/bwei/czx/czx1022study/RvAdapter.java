package com.bwei.czx.czx1022study;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by czx on 2017/10/22.
 */

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<UserBean> list;

    public RvAdapter(Context context, List<UserBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder)holder;
            if(list.get(position).isB()){
                viewHolder.item_ck.setChecked(true);
            }else{
                viewHolder.item_ck.setChecked(false);
            }
            viewHolder.item_name.setText(list.get(position).getPrice()+"");
            viewHolder.item_ck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = list.get(position).isB();
                    list.get(position).setB(!isChecked);
                    notifyDataSetChanged();
                    if(listener != null){
                        listener.check(!isChecked,position);
                    }
                }
            });

//            viewHolder.item_ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                }
//            });

            //监听Edtext
            viewHolder.item_custom.setListener(new CustomView.ChangeListener() {
                @Override
                public void onChange(long count) {
                    list.get(position).setCount(count);
                    notifyDataSetChanged();
                    if(listener != null){
                        listener.check(list.get(position).isB(),position);
                    }
                }
            });



        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    //为check写一个接口
    public CheckListener listener;
    public void setCheckListener(CheckListener listener){
        this.listener = listener;
    }

    interface CheckListener{
        public void check(boolean check,int position);
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private final CustomView item_custom;
        private final TextView item_name;
        private final CheckBox item_ck;

        public ViewHolder(View itemView) {
            super(itemView);
            item_custom = (CustomView) itemView.findViewById(R.id.item_custom);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_ck = (CheckBox) itemView.findViewById(R.id.item_ck);
        }
    }
}
