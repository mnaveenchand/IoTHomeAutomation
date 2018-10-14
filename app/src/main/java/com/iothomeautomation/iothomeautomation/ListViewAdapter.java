package com.iothomeautomation.iothomeautomation;

/**
 * Created by Juned on 6/17/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter
{
    Context context;

    List<Subject> TempSubjectList;

    public ListViewAdapter(List<Subject> listValue, Context context)
    {
        this.context = context;
        this.TempSubjectList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.TempSubjectList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.TempSubjectList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater )this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listview_items, null);

            viewItem.DateView = (TextView )convertView.findViewById(R.id.date);

            viewItem.TemperatureView = (TextView )convertView.findViewById(R.id.temparature);

            viewItem.HumidityView = (TextView )convertView.findViewById(R.id.humi);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.DateView.setText(TempSubjectList.get(position).Subject_Date);

        viewItem.TemperatureView.setText(TempSubjectList.get(position).Subject_Temp);

        viewItem.HumidityView.setText(TempSubjectList.get(position).Subject_Humi);

        return convertView;
    }
}

class ViewItem
{
    TextView DateView;
    TextView TemperatureView;
    TextView HumidityView;
}