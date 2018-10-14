package com.iothomeautomation.iothomeautomation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iothomeautomation.iothomeautomation.Model.ModelKeyData;
import com.iothomeautomation.iothomeautomation.R;
import com.iothomeautomation.iothomeautomation.keyPadView;

import java.util.List;

/**
 * Created by arunt on 16/03/2018.
 */

public class AdapterDataKey extends RecyclerView.Adapter<AdapterDataKey.HolderData>{

    private List<ModelKeyData>nItems;


    public AdapterDataKey(keyPadView keyPadView, List<ModelKeyData> items)
    {
        this.nItems = items;

    }


    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyrow,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelKeyData md  = nItems.get(position);
        holder.date.setText(md.getDate());
        holder.login.setText(md.getLogin());

        holder.md = md;


    }

    @Override
    public int getItemCount() {
        return nItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView date,login;
        ModelKeyData md;

        public  HolderData (View view)
        {
            super(view);

            date = (TextView) view.findViewById(R.id.Date);
            login = (TextView) view.findViewById(R.id.Login);


        }
    }

}
