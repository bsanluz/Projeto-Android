package com.example.brunoluz.pj.Adapter;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;

/**
 * Created by Bruno Luz on 29/07/2016.
 */
public class AsyncTaskLoadFiles extends AsyncTask<Void, String, Void> {

    File targetDirector;
    ImageAdapter myTaskAdapter;

    public AsyncTaskLoadFiles(ImageAdapter adapter) {
        myTaskAdapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        String ExternalStorageDirectoryPath = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();

        String targetPath = ExternalStorageDirectoryPath;
        targetDirector = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "StyPhotos");
        myTaskAdapter.clear();

        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        File[] files = targetDirector.listFiles();
        for (File file : files) {
            publishProgress(file.getAbsolutePath());
            if (isCancelled()) break;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        myTaskAdapter.add(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void result) {
        myTaskAdapter.notifyDataSetChanged();
        super.onPostExecute(result);
    }

}
