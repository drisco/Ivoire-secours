package com.example.medipass;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.medipass.models.ModelUserLogin;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Deflater;

public class RegisterInfo extends AppCompatActivity {
    PopupRegister popusCostum;


    private EditText prenomEditText;
    private EditText nomEditText;
    private EditText mailEditText;
    private EditText pwdEditText;
    private EditText vpwdEditText;
    private TextView signGoogle;
    int incr;
    GoogleSignInOptions gso;
    private RelativeLayout btn1TextView,btn2TextView;
    private static final int RC_SIGN_IN = 123;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DatabaseReference databaseReference;
    String idQrcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_info);
         databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
         idQrcode = getIntent().getStringExtra("idQrcode");
        System.out.println("RJGFHGJHRUHGJHERIJHGIUHEGIUJGITJG IDQRCODE "+idQrcode);
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        prenomEditText = findViewById(R.id.prenom);
        nomEditText = findViewById(R.id.nom);
        mailEditText = findViewById(R.id.mail);
        pwdEditText = findViewById(R.id.pwd);
        vpwdEditText = findViewById(R.id.vpwd);
        signGoogle = findViewById(R.id.textView2);
        // Récupérer les références des boutons
        btn1TextView = findViewById(R.id.btn1);
        btn2TextView = findViewById(R.id.btn2);

        signGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterInfo.this,Login.class));
                finish();
            }
        });

        btn1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(RegisterInfo.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                if (!prenomEditText.getText().toString().isEmpty() || !nomEditText.getText().toString().isEmpty()
                || !mailEditText.getText().toString().isEmpty()|| !pwdEditText.getText().toString().isEmpty()|| !vpwdEditText.getText().toString().isEmpty()){

                    if (pwdEditText.getText().toString().equals(vpwdEditText.getText().toString())){
                        AjouterDonneesUtilisateur(prenomEditText.getText().toString(),nomEditText.getText().toString(),mailEditText.getText().toString(),pwdEditText.getText().toString());
                    }else {
                        popusCostum.dismiss();
                        Toast.makeText(RegisterInfo.this, "Les Mot de Passe ne sont pas identiques", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    popusCostum.dismiss();
                    Toast.makeText(RegisterInfo.this, "Veuillez remplir tout les champs Svp", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void AjouterDonneesUtilisateur(String prenom, String nom, String email, String mdp) {
        System.out.println("GNFJNNKJRNFNNJNKRNFNKJNF");

        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // L'utilisateur existe déjà avec ce numéro de téléphone, affichez un message d'erreur
                    Toast.makeText(getApplicationContext(), "Ce numéro de téléphone est déjà associé à un compte.", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("RJHRHJBHREIHGREBKJFBJR RJHOUFBRHJGRHJOERHIOS "+"ERJKGFB?NJNFNJKNKNNJGNFJNNKJRNFNNJNKRNFNKJNF");
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
                    String dateFormatted = dateFormat.format(date);

                    ModelUserLogin user =new ModelUserLogin(idQrcode,prenom,nom,email,mdp,dateFormatted,"","",
                            "","","","","");
                    databaseReference.child(idQrcode).setValue(user);
                    editor.putString("id", idQrcode);
                    editor.putString("prenom", prenom);
                    editor.putString("nom", nom);
                    editor.putString("email", email);
                    editor.apply();
                    popusCostum.dismiss();
                    startActivity(new Intent(RegisterInfo.this,Profile.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                popusCostum.dismiss();
            }
        });

    }
    @Override
    public void onBackPressed() {
        incr++;
        if (incr==1){
            super.onBackPressed();
            startActivity(new Intent(RegisterInfo.this,Register.class));
            finish();
        }
    }

}