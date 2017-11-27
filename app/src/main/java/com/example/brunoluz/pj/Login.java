package com.example.brunoluz.pj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button login, cadastro;
    private EditText email, senha;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        login = (Button) findViewById(R.id.bttLogin);
        cadastro = (Button) findViewById(R.id.bttNovoUser);
        email = (EditText) findViewById(R.id.editTextEmail);
        senha = (EditText) findViewById(R.id.editTextSenha);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_login);
        login.setOnClickListener(this);
        cadastro.setOnClickListener(this);
        progressBar.setVisibility(View.INVISIBLE);
    }
    @Override
    public void onClick(View v) {
        if(v == cadastro){
            Intent intent = new Intent(Login.this, Cadastro.class);
            startActivity(intent);
            finish();
        }
        if(v == login){
            String mail, sen;
            mail = email.getText().toString();
            sen = senha.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(mail,sen).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login falhou",Toast.LENGTH_SHORT).show();
                    }else{
                    finish();
                    progressBar.setVisibility(View.GONE);}
                }
            });
        }
    }
}
