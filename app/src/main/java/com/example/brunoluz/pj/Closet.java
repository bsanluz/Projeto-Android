package com.example.brunoluz.pj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.brunoluz.pj.Adapter.AsyncTaskLoadFiles;
import com.example.brunoluz.pj.Adapter.ImageAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Closet extends AppCompatActivity {
    FloatingActionButton camera;
    GridView imagegrid;
    private ImageAdapter myImageAdapter;
    AsyncTaskLoadFiles myAsyncTaskLoadFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);
        imagegrid = (GridView) findViewById(R.id.gridView_fotos);
        myImageAdapter = new ImageAdapter(this);
        imagegrid.setAdapter(myImageAdapter);
        myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
        myAsyncTaskLoadFiles.execute();

        imagegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Closet.this,mostraFoto.class);
                String s = adapterView.getItemAtPosition(i).toString();
                intent.putExtra("img",s);
                startActivity(intent);
            }
        });
        imagegrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String prompt = "remove " + (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), prompt, Toast.LENGTH_SHORT).show();
                myImageAdapter.remove(i);
                myImageAdapter.notifyDataSetChanged();
                return true;
            }
        });
        camera = (FloatingActionButton) findViewById(R.id.fab);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), "StyPhotos");
                //Cria o diretorio caso não exista
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        Log.d("StyPhotos", "Erro ao criar pasta");
                    }
                }
                // Cria o nome do arquivo
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File mediaFile;
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "IMG_" + timeStamp + ".jpg");
                Uri outputfile = Uri.fromFile(mediaFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputfile);
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        myAsyncTaskLoadFiles.cancel(true);
        myImageAdapter = new ImageAdapter(Closet.this);
        imagegrid.setAdapter(myImageAdapter);
        myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(myImageAdapter);
        myAsyncTaskLoadFiles.execute();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            onResume();
        }
    }
}

