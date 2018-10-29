package com.example.ericmartins.echoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ericmartins.echoapp.DAO.ConfiguracaoFirebase;
import com.example.ericmartins.echoapp.Entidades.Usuarios;
import com.example.ericmartins.echoapp.Helper.Base64Custom;
import com.example.ericmartins.echoapp.Helper.Preferencias;
import com.example.ericmartins.echoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    public EditText edtCadNome;
    public EditText edtCadSobrenome;
    public EditText edtCadEmail;
    public EditText edtCadSenha;
    public EditText edtCadConfirmarSenha;
    public Button btnGravar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCadNome = (EditText) findViewById(R.id.edtCadNome);
        edtCadSobrenome= (EditText)findViewById(R.id.edtCadSobrenome);
        edtCadEmail = (EditText)findViewById(R.id.edtCadEmail);
        edtCadSenha = (EditText)findViewById(R.id.edtCadSenha);
        edtCadConfirmarSenha = (EditText)findViewById(R.id.edtCadConfirmaSenha);
        btnGravar = (Button) findViewById(R.id.btnGrava);

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(edtCadConfirmarSenha.getText().toString())) {
                    usuarios = new Usuarios();
                    usuarios.setNome(edtCadNome.getText().toString());
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());
                    usuarios.setConfirmarSenha(edtCadConfirmarSenha.getText().toString());

                    cadastrarUsuario();
                } else {
                    Toast.makeText(CadastroActivity.this, "Senhas não compatíveis", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void cadastrarUsuario(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(CadastroActivity.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getNome());

                    abrirLoginUsuario();
                } else {
                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e ){
                        erroExcecao = "Digite uma senha mais forte,  contendo pelo menos 8 caracteres de letras e números";
                    } catch (FirebaseAuthInvalidCredentialsException e ){
                        erroExcecao = "E-mail inválido, digite um novo e-mail";
                    } catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Esse e-mail já está cadastrado no sistema";
                    } catch (Exception e){
                        erroExcecao = "Erro ao efetuar o cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "ERRO: " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
