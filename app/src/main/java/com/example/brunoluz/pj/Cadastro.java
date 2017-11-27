package com.example.brunoluz.pj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.brunoluz.pj.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {
    private EditText nome_Cadast, idade_Cadast, email_Cadast, senha_Cadast;
    private RadioButton checkMas, checkFem;
    private Button cadastrar;
    private User usuario;
    private String genero;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setViews();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(Cadastro.this, MainActivity.class);
                    usuario.setId(mAuth.getCurrentUser().getUid().toString());
                    salvarUser(usuario);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        };
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosUser();
                criarUser();
            }
        });
    }
    //// inicia as views////
    private void setViews() {
        nome_Cadast = (EditText) findViewById(R.id.editText_NomeCada);
        idade_Cadast = (EditText) findViewById(R.id.editText_Idade);
        email_Cadast = (EditText) findViewById(R.id.editText_EmailCadastro);
        senha_Cadast = (EditText) findViewById(R.id.editText_SenhaCadastro);
        cadastrar = (Button) findViewById(R.id.bttCadastrar);
        checkMas = (RadioButton) findViewById(R.id.radioButton_Masc);
        checkFem = (RadioButton) findViewById(R.id.radioButton_Fem);
    }
    /// coleta os dados do usuario ///
    private void dadosUser() {
        usuario = new User();
        usuario.setNome(nome_Cadast.getText().toString());
        usuario.setIdade(idade_Cadast.getText().toString());
        usuario.setEmail(email_Cadast.getText().toString());
        usuario.setSenha(senha_Cadast.getText().toString());
        if (checkMas.isChecked()) {
            checkFem.setChecked(false);
            genero = "Masculino";
        } else if (checkFem.isChecked()) {
            checkMas.setChecked(false);
            genero = "Feminino";
        }
        usuario.setGenero(genero);
    }
    ///CRIAR USUARIO PASSANDO SENHA E EMAIL////
    private void criarUser(){
        String email, senha;
        email =  usuario.getEmail();
        senha =  usuario.getSenha();
        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                Toast.makeText(Cadastro.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    ////SALVA USUARIO NO BANCO//////
    public void salvarUser(User user){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("projeto-8e991");
        myRef.child("users").child(user.getId()).setValue(user);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
