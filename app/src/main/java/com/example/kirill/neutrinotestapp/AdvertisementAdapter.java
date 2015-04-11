package com.example.kirill.neutrinotestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kirill.neutrinotestapp.objects.AdvertisementItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by iiopok on 11.04.2015.
 */
public class AdvertisementAdapter extends ArrayAdapter<AdvertisementItem> {

    private final List<AdvertisementItem> list;
    private final Context context;

    public AdvertisementAdapter(Context context, List<AdvertisementItem> objects) {
        super(context, R.layout.advertisement_item, objects);
        this.list = objects;
        this.context = context;
    }

    static class ViewHolder {
        protected TextView name;
        protected TextView description;
        protected TextView created;
        protected ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageLoader imageLoader = ImageLoader.getInstance();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.MIN_PRIORITY)
                .threadPoolSize(5)
                .imageDownloader(new StreamImageDownloader(context))
                .build();
        imageLoader.init(configuration);


        View view = null;
        if (convertView == null) {
            LayoutInflater lInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = lInflater.inflate(R.layout.advertisement_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.tvName);
            viewHolder.description = (TextView) view.findViewById(R.id.tvDescription);
            viewHolder.created = (TextView) view.findViewById(R.id.tvCreated);
            viewHolder.image = (ImageView) view.findViewById(R.id.ivImage);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.name.setText(list.get(position).getName());
        holder.description.setText(list.get(position).getDescription());
        holder.created.setText(list.get(position).getCreatedAt());
        if(list.get(position).getStream()!=null) {
            String imageId = "stream://" + list.get(position).getStream().hashCode();
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .extraForDownloader(list.get(position).getStream())
                    .build();
            imageLoader.displayImage(imageId, holder.image, options);
        }
        return view;
    }
}
