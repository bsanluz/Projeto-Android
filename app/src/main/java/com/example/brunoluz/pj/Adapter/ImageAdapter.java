package com.example.brunoluz.pj.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.brunoluz.pj.R;

import java.util.ArrayList;

public class ImageAdapter  extends BaseAdapter {

    private Context mContext;
    ArrayList<String> itemList = new ArrayList<String>();

    public ImageAdapter(Context c) {
        mContext = c;
    }

    void add(String path) {
        itemList.add(path);
    }

    void clear() {
        itemList.clear();
    }

    public void remove(int index){
        itemList.remove(index);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.galerryitem,null);
            holder = new viewHolder(convertView);
            convertView.setTag(holder);
            holder.photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.photo.setPadding(8,8,8,8);
            //holder.photo.setRotation(90);
        }else{
            holder = (viewHolder) convertView.getTag();
        }
        Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 200, 200);
        holder.photo.setImageBitmap(bm);
        return convertView;
    }

    public Bitmap decodeSampledBitmapFromUri(String path, int reqWidth,
                                             int reqHeight) {

        Bitmap bm = null;
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
       options.inSampleSize = calculateInSampleSize(options, reqWidth,
              reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    public int calculateInSampleSize(

            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height
                        / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }

        return inSampleSize;
    }

    class viewHolder{
        ImageView photo;

        viewHolder(View v){
            photo = (ImageView) v.findViewById(R.id.imageView);
        }
    }

}