package com.example.brunoluz.pj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.brunoluz.pj.model.Loja;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class cadastroLoja extends AppCompatActivity {
    private EditText nomeLoja, enderecoLoja, contantoLoja, cidadeLoja;
    private Spinner estado, tipoLoja;
    private FirebaseAuth mAuth;
    Button cadastra;
    Loja loja;
    ArrayList<String> estados;
    ArrayList<String> tipos;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_loja);
        mAuth = FirebaseAuth.getInstance();
        initView();
        initArray();
        initSpinner();
        cadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dadosLoja();
                salvarloja(loja);
                finish();
            }
        });
    }
    private void initView(){
        nomeLoja = (EditText) findViewById(R.id.editText_NomeLoja);
        enderecoLoja= (EditText) findViewById(R.id.editText_EnderecoLoja);
        contantoLoja= (EditText) findViewById(R.id.editText_ContatoLoja);
        cidadeLoja= (EditText) findViewById(R.id.editText_CidadeLoja);
        estado= (Spinner) findViewById(R.id.spinnerEstado);
        tipoLoja= (Spinner) findViewById(R.id.spinnerTipo);
        cadastra= (Button) findViewById(R.id.button_cadastroLoja);
    }
    private void initArray(){
        estados = new ArrayList<String>();
        estados.add("RS");
        estados.add("SC");
        estados.add("PR");
        estados.add("Outro");
        tipos = new ArrayList<String>();
        tipos.add("Moda Masculina");
        tipos.add("Calçados");
        tipos.add("Moda Feminina");
        tipos.add("Moda Intima");
        tipos.add("Acessórios");
        tipos.add("Outro");
    }
    private void initSpinner(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, estados);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        estado.setAdapter(spinnerArrayAdapter);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tipos);
        ArrayAdapter<String> spinnerArrayAdapter2 = arrayAdapter2;
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        tipoLoja.setAdapter(spinnerArrayAdapter2);
    }
    private void dadosLoja(){
        loja = new Loja();
        loja.setNomeLoja(nomeLoja.getText().toString());
        loja.setEnderecoLoja(enderecoLoja.getText().toString());
        loja.setContatoTel(contantoLoja.getText().toString());
        loja.setCidadeLoja(cidadeLoja.getText().toString());
        loja.setEstadoLoja(estado.getSelectedItem().toString());
        loja.setTipoLoja(tipoLoja.getSelectedItem().toString());
    }
    public void salvarloja(Loja loja){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("projeto-8e991");
        myRef.child("lojas").push().setValue(loja);
    }
}
