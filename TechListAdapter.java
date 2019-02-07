package com.example.melani.mas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TechListAdapter extends BaseAdapter{


    private Context context;
    private ArrayList<AvailableTechnicianModel> techModelArrayList;
    private String SerialNum;

    public TechListAdapter(Context context, ArrayList<AvailableTechnicianModel> techModelArrayList,String SerialNum) {

        this.context = context;
        this.techModelArrayList = techModelArrayList;
        this.SerialNum = SerialNum;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return techModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return techModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.technicianlist, null, true);

            holder.iv = (ImageView) convertView.findViewById(R.id.pro);
            holder.tvname = (TextView) convertView.findViewById(R.id.tvName);
            holder.Card = (CardView)convertView.findViewById(R.id.card);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        Picasso.get().load(techModelArrayList.get(position).getImg()).into(holder.iv);
        holder.tvname.setText("Name: "+techModelArrayList.get(position).getTechname());
        holder.Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AsignTechnicianActivity.class);
                intent.putExtra("Techname",techModelArrayList.get(position).getTechname().toString());
                intent.putExtra("Serial",SerialNum);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname;
        protected ImageView iv;
        protected CardView Card;
    }
}
