package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends BaseAdapter {

    Context myContext;
    int myLayout;
    List<News> arrayNews;
    public NewsAdapter(Context context, int layout, List<News> newsList){
        myContext = context;
        myLayout = layout;
        arrayNews = newsList;

    }
    @Override
    public int getCount() {
        return arrayNews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);

        //Ánh xạ và gán giá trị
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        txtTitle.setText(arrayNews.get(position).title);

        TextView txtDesc = (TextView) convertView.findViewById(R.id.desc);
        txtDesc.setText(arrayNews.get(position).desc);

        TextView txtDate = (TextView) convertView.findViewById(R.id.date);
        txtDate.setText(arrayNews.get(position).date);

//        ImageView image = convertView.findViewById(R.id.image_item);
//        String urlImage = arrayNews.get(position).getUrl();
//        Picasso.get().load(urlImage).into(image);

        return convertView;
    }
}
