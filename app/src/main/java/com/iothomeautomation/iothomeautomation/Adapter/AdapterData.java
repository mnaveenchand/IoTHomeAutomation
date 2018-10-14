package com.iothomeautomation.iothomeautomation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.iothomeautomation.iothomeautomation.Model.ModelData;
import com.iothomeautomation.iothomeautomation.RFinsertData;
import com.iothomeautomation.iothomeautomation.R;

import java.util.List;

/**
 * Created by hakiki95 on 11/30/2016.
 */

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems ;
    private Context context;

    public AdapterData(Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.rfrow,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md  = mItems.get(position);
        holder.tvName.setText(md.getName());
        holder.tvTime.setText(md.getTime());
        holder.tvTagg.setText(md.getTagg());
        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvName,tvTime,tvTagg;
        ModelData md;

        public  HolderData (View view)
        {
            super(view);

            tvName = (TextView ) view.findViewById(R.id.Name);
            tvTime = (TextView ) view.findViewById(R.id.Time);
            tvTagg = (TextView ) view.findViewById(R.id.Tags);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, RFinsertData.class);
                    update.putExtra("update",1);
                    update.putExtra("Time",md.getTime());
                    update.putExtra("Name",md.getName());
                    update.putExtra("Tag",md.getTagg());
                    update.putExtra("Uid",md.getUid());


                    context.startActivity(update);
                }
            });
        }
    }
}
