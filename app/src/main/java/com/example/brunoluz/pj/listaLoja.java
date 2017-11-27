package com.example.brunoluz.pj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brunoluz.pj.Adapter.LojaAdapter;
import com.example.brunoluz.pj.model.Loja;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listaLoja extends AppCompatActivity{
    FloatingActionButton adicionarLoja;
    ListView lv;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    ArrayList<Loja> lojas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_loja);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("projeto-8e991");
        lv = (ListView) findViewById(R.id.listView);
        carregaLista();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lv.getAdapter().getItem(i);
                String s = (String) ((TextView) view.findViewById(R.id.textView_Endereco)).getText();
                String n = (String) ((TextView) view.findViewById(R.id.textView_NomeLola)).getText();
                Intent intent = new Intent(listaLoja.this,MapsActivity.class);
                intent.putExtra("nome",n);
                intent.putExtra("endereco",s);
                startActivity(intent);
            }
        });
        adicionarLoja = (FloatingActionButton) findViewById(R.id.fab_adicionarLoja);
        adicionarLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listaLoja.this, cadastroLoja.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
    private void carregaLista(){
        myRef.child("lojas").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        lojas.clear();
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Loja loja = postSnapshot.getValue(Loja.class);
                            lojas.add(loja);

                        }
                        lv.setAdapter(new LojaAdapter(listaLoja.this,lojas));;// ...
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}
