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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import org.w3c.dom.Text;





public class ForumCustomBaseAdapter extends BaseAdapter 
{
    Context context;
    List<RowItemForumItem> rowItems;
  

    public ForumCustomBaseAdapter(Context context, List<RowItemForumItem> items) {
       
    	this.context = context;
        this.rowItems = items;
        
    }
    

    

	/*private view holder class*/
    private class ViewHolder {
        TextView txtUsername;
        TextView txtDescription;
        TextView day;
        TextView time;
        
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.feed_row, null);
            holder = new ViewHolder();
            
            holder.txtUsername = (TextView) convertView.findViewById(R.id.feedUser);
            holder.txtDescription=(TextView)convertView.findViewById(R.id.feedData);
            holder.day=(TextView)convertView.findViewById(R.id.feedTime);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        RowItemForumItem rowItem = (RowItemForumItem) getItem(position);

        
        holder.txtDescription.setText(rowItem.getDescription());
        holder.txtUsername.setText(rowItem.getUsername());
        holder.day.setText(rowItem.getDay());
       // holder.txtTime.setText(rowItem.getTime());

        
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