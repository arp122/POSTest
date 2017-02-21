package com.example.posttest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;





public class ForumMenuCustomBaseAdapter extends BaseAdapter 
{
    Context context;
    List<ForumMenuRowItem> rowItems;
  

    public ForumMenuCustomBaseAdapter(Context context, List<ForumMenuRowItem> items) {
        this.context = context;
        this.rowItems = items;
        
    }

    

	/*private view holder class*/
    private class ViewHolder {
        TextView txtTitle;
        
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.forum_menu_feed_row, null);
            holder = new ViewHolder();
            
            holder.txtTitle = (TextView) convertView.findViewById(R.id.feedTitle);
                       convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ForumMenuRowItem rowItem = (ForumMenuRowItem) getItem(position);

        
        holder.txtTitle.setText(rowItem.getTitle());
               
        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
}