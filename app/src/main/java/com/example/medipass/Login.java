package com.example.medipass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
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

import com.example.medipass.models.DonneesMedicales;
import com.example.medipass.models.ModelUserLogin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText mail,pwd;
    RelativeLayout boutton;
    TextView emailLogin;
    DatabaseReference databaseReference,databaseReference1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String patri,sexe;
    PopupRegister popusCostum;
    int incr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        mail =findViewById(R.id.mail);
        pwd =findViewById(R.id.pwd);
        boutton =findViewById(R.id.btn1l);
        emailLogin =findViewById(R.id.emailLogin);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("donneesmedicale");
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
         patri = sharedPreferences.getString("patrimo", "");
         sexe = sharedPreferences.getString("sexe", "");
        boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mail.getText().toString().isEmpty()|| !pwd.getText().toString().isEmpty()){
                    popusCostum = new PopupRegister(Login.this);
                    popusCostum.setCancelable(false);
                    popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popusCostum.show();
                    if (mail.getText().toString().equals("medipass@gmail.com") && pwd.getText().toString().equals("medipassadmin123")){
                        editor.putString("nom", "Admin");
                        editor.putString("prenom", "Médipass");
                        editor.putString("mdp","medipassadmin123" );
                        editor.putString("numero", "+225 0503271868");
                        editor.putString("image", "");
                        editor.apply();
                        startActivity(new Intent(Login.this,ProfilePanel.class));
                        finish();

                    }else{
                        login(mail.getText().toString(),pwd.getText().toString());
                    }
                }else{
                    popusCostum.dismiss();
                    Toast.makeText(Login.this, "Veuillez verifier vos champs!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        emailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

        private void login(String email,String password) {

            databaseReference.orderByChild("mail").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Vérifiez si dataSnapshot contient des données
                    if (dataSnapshot.exists()) {
                        // L'utilisateur existe, parcourir les données pour vérifier le mot de passe
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            ModelUserLogin user = userSnapshot.getValue(ModelUserLogin.class);


                            // Vérifiez si le mot de passe correspond
                            if (user != null && user.getPwd().equals(password)) {
                                //idPBLoading.setVisibility(View.GONE);

                                editor.putString("id", user.getId());
                                editor.putString("nom", user.getNom());
                                editor.putString("prenom", user.getPrenom());
                                editor.putString("mdp", user.getPwd());
                                editor.putString("image", user.getPhoto());
                                editor.putString("numero", user.getNumero());
                                editor.putString("otherNume", user.getOtherNumero());
                                editor.putString("pays", user.getPays());
                                editor.putString("ville", user.getVille());
                                editor.putString("quartier", user.getQuartier());
                                editor.putString("adress", user.getAdress());
                                editor.apply();
                                    databaseReference1.child(user.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                DonneesMedicales donnees = dataSnapshot.getValue(DonneesMedicales.class);

                                                editor.putString("sexe", donnees.getSexeVictime());
                                                editor.putString("dateDeNaissance", donnees.getDateNaissanceVictime());
                                                editor.putString("numeroDeSecu", donnees.getAutreNumeroSecoureur());
                                                editor.putString("prenomcu", donnees.getPrenomSecoureur());
                                                editor.putString("nomcu", donnees.getNomSecoureur());
                                                editor.putString("adressVictime", donnees.getAdressVictime());
                                                editor.putString("typeRelation", donnees.getRelationAvecSecoureur());
                                                editor.putString("autreNumcu", donnees.getAutreNumeroSecoureur());
                                                editor.putString("groupe", donnees.getGroupeSanguinVictime());
                                                editor.putString("poids", donnees.getPoidsVictime());
                                                editor.putString("allergies", donnees.getAllergies());
                                                editor.putString("medicaments", donnees.getMediRegulierVictime());
                                                editor.putString("assureurNon", donnees.getNomAssureure());
                                                editor.putString("policeNum", donnees.getPoliceNumero());
                                                editor.putString("numSecuriteSocial", donnees.getNumeroSecuriteSocial());
                                                editor.putString("cmpreexistantes", donnees.getCompriPreexistant());
                                                editor.putString("numeroMedecin", donnees.getNumeroMedecin());
                                                editor.putString("dma", donnees.getDirectionMedicale());
                                                editor.apply();
                                                popusCostum.dismiss();
                                                startActivity(new Intent(Login.this,ProfilePanel.class));
                                                finish();

                                            } else {
                                                popusCostum.dismiss();
                                                startActivity(new Intent(Login.this,ProfilePanel.class));
                                                finish();                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            popusCostum.dismiss();
                                        }
                                    });


                            } else {
                                popusCostum.dismiss();
                                Toast.makeText(getApplicationContext(), "Mot de passe incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        popusCostum.dismiss();
                        Toast.makeText(getApplicationContext(), "Aucun utilisateur trouvé avec ce numéro de téléphone.", Toast.LENGTH_SHORT).show();
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
            //finish();
            finishAffinity();
        }
    }
    }
