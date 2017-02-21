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





public class Opinion_Poll_CustomBaseAdapter extends BaseAdapter 
{
    Context context;
    List<Opinion_Poll_RowItem> rowItems;
  

    public Opinion_Poll_CustomBaseAdapter(Context context, List<Opinion_Poll_RowItem> items) {
        this.context = context;
        this.rowItems = items;
        
    }

    

	/*private view holder class*/
    private class ViewHolder {
        TextView txtTitle;
        TextView party;
        ProgressBar cand_progress;
        TextView cand_progint;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.opinion_poll_feed_row, null);
            holder = new ViewHolder();
            
            holder.txtTitle = (TextView) convertView.findViewById(R.id.feedTitle);
            holder.party = (TextView)convertView.findViewById(R.id.Party);
            holder.cand_progress = (ProgressBar)convertView.findViewById(R.id.cand_progress);
            holder.cand_progint = (TextView)convertView.findViewById(R.id.prog_int);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Opinion_Poll_RowItem rowItem = (Opinion_Poll_RowItem) getItem(position);

        
        holder.txtTitle.setText(rowItem.getTitle());
        holder.party.setText(rowItem.getParty());
        holder.cand_progress.setProgress(rowItem.getProgress());
        holder.cand_progint.setText(rowItem.getProgress()+"%");
        
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