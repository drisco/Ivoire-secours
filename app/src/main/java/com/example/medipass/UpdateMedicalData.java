package com.example.medipass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.medipass.models.DonneesMedicales;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateMedicalData extends AppCompatActivity {
    ScrollView info_urgence,info_medical,dataUser;
    LinearLayout dp,cu,im;
    private RelativeLayout annulationbtn,sauvegardebtn;
    DatabaseReference databaseReference;
    int incr;


    EditText nometprenom,sexe,date_de_nai,adresse,numero,autrenum,prenomcu,nomcu,type_relation
            ,numerocu,autrenumcu,groupe,poids,allergies
            ,medicaments_regu,cmpreexistantes,nom_assureur,policeNum,num_securite_social,direction_medicale;


    TextView session1,dpt,cut,imt,next,deconnecter;
    ImageView dpbtn,cubtn,imbtn,mon_profil,acceuil;
    CircleImageView photo1,photo;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    PopupRegister popusCostum;
    int currentStep = 1;
    String id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_medical_data);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("donneesmedicale");

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        id = sharedPreferences.getString("id", "");
        String photos = sharedPreferences.getString("image", "");
        String numero1 = sharedPreferences.getString("numero", "");
        String adress = sharedPreferences.getString("adress", "");
        String prenom = sharedPreferences.getString("prenom", "");
        String nom = sharedPreferences.getString("nom", "");
        String numeroMedecin = sharedPreferences.getString("numeroMedecin", "");

        String sexe1 = sharedPreferences.getString("sexe", "");
        String dateDeNaissance1 = sharedPreferences.getString("dateDeNaissance", "");
        String numeroDeSecu1 = sharedPreferences.getString("numeroDeSecu", "");
        String prenomcu1 = sharedPreferences.getString("prenomcu", "");
        String nomcu1 = sharedPreferences.getString("nomcu", "");
        String typeRelation1 = sharedPreferences.getString("typeRelation", "");
        String autreNumcu1 = sharedPreferences.getString("autreNumcu", "");
        String groupe1 = sharedPreferences.getString("groupe", "");
        String poids1 = sharedPreferences.getString("poids", "");
        String medicaments1 = sharedPreferences.getString("medicaments", "");
        String allergies1 = sharedPreferences.getString("allergies", "");
        String assureurNon1 = sharedPreferences.getString("assureurNon", "");
        String policeNum1 = sharedPreferences.getString("policeNum", "");
        String numSecuriteSocial1 = sharedPreferences.getString("numSecuriteSocial", "");
        String dma1 = sharedPreferences.getString("dma", "");
        String cmpreexistantes1 = sharedPreferences.getString("cmpreexistantes", "");



        photo1 = findViewById(R.id.userdashbord);
        acceuil = findViewById(R.id.acceuil);
        deconnecter = findViewById(R.id.session);
        Bitmap bip =ImageUtils.stringToBitmap(photos);
        photo1.setImageBitmap(bip);
        deconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear(); // Supprimer toutes les données
                editor.apply();
                startActivity(new Intent(UpdateMedicalData.this, Login.class));
                finish();
            }
        });
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateMedicalData.this, ProfilePanel.class));
                finish();
            }
        });

        // scrollview1
        nometprenom = findViewById(R.id.smDu);
        nometprenom.setText(nom+ " "+prenom);
        sexe = findViewById(R.id.sexeDu);
        sexe.setText(sexe1);
        date_de_nai = findViewById(R.id.hbdDu);
        date_de_nai.setText(dateDeNaissance1);
        adresse = findViewById(R.id.adresseDu);
        adresse.setText(adress);
        numero = findViewById(R.id.numeroDu);
        numero.setText(numero1);
        autrenum = findViewById(R.id.autrenumDu);
        autrenum.setText(numeroMedecin);


        //scrollview2
        prenomcu=findViewById(R.id.prenomCu);
        prenomcu.setText(prenomcu1);
        nomcu=findViewById(R.id.nomCu);
        nomcu.setText(nomcu1);
        type_relation=findViewById(R.id.relationCu);
        type_relation.setText(typeRelation1);
        numerocu = findViewById(R.id.telCu);
        numerocu.setText(numeroDeSecu1);
        autrenumcu = findViewById(R.id.autrenumCu);
        autrenumcu.setText(autreNumcu1);


        //Scrollview3
        groupe=findViewById(R.id.sangim);
        groupe.setText(groupe1);
        poids=findViewById(R.id.poids);
        poids.setText(poids1);
        allergies=findViewById(R.id.allergieim);
        allergies.setText(allergies1);
        medicaments_regu=findViewById(R.id.medocim);
        medicaments_regu.setText(medicaments1);
        cmpreexistantes=findViewById(R.id.chirugieim);
        cmpreexistantes.setText(cmpreexistantes1);
        nom_assureur=findViewById(R.id.assuranceim);
        nom_assureur.setText(assureurNon1);
        policeNum=findViewById(R.id.num_assurance_im);
        policeNum.setText(policeNum1);
        num_securite_social=findViewById(R.id.num_securite_social);
        num_securite_social.setText(numSecuriteSocial1);
        direction_medicale=findViewById(R.id.dmaIm);
        direction_medicale.setText(dma1);

        next = findViewById(R.id.suivant);
        sauvegardebtn = findViewById(R.id.sauvegardebtn);
        annulationbtn = findViewById(R.id.annulationbtn);
        info_urgence = findViewById(R.id.info_urgence);
        info_medical = findViewById(R.id.info_medical);
        dataUser = findViewById(R.id.dataUser);
        dp = findViewById(R.id.dp);
        cu = findViewById(R.id.cu);
        im = findViewById(R.id.im);
        imt = findViewById(R.id.imt);
        cut = findViewById(R.id.cut);
        dpt = findViewById(R.id.dpt);
        dpbtn = findViewById(R.id.dpBtn);
        cubtn = findViewById(R.id.cuBtn);
        imbtn = findViewById(R.id.imBtn);

        dpbtn.setVisibility(View.VISIBLE);
        cubtn.setVisibility(View.GONE);
        imbtn.setVisibility(View.GONE);

        annulationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateMedicalData.this, ProfilePanel.class));
                finish();
            }
        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStep=1;
                dataUser.setVisibility(View.VISIBLE);
                info_urgence.setVisibility(View.GONE);
                info_medical.setVisibility(View.GONE);

                dpbtn.setVisibility(View.VISIBLE);
                cubtn.setVisibility(View.GONE);
                imbtn.setVisibility(View.GONE);
                next.setText("Suivant");
            }
        });
        sauvegardebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStep++;
                if (currentStep == 2) {
                    dpbtn.setVisibility(View.GONE);
                    cubtn.setVisibility(View.VISIBLE);
                    imbtn.setVisibility(View.GONE);

                    dataUser.setVisibility(View.GONE);
                    info_urgence.setVisibility(View.VISIBLE);
                } else if (currentStep == 3) {
                    dpbtn.setVisibility(View.GONE);
                    cubtn.setVisibility(View.GONE);
                    imbtn.setVisibility(View.VISIBLE);

                    info_urgence.setVisibility(View.GONE);
                    info_medical.setVisibility(View.VISIBLE);
                    next.setText("Terminer");
                    if (next.getText().toString().equals("Terminer")){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                popusCostum = new PopupRegister(UpdateMedicalData.this);
                                popusCostum.setCancelable(false);
                                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                popusCostum.show();
                                ajouterLesDonneeesMedicales(sexe.getText().toString(),date_de_nai.getText().toString(),adresse.getText().toString()
                                        ,autrenum.getText().toString(),prenomcu.getText().toString(),nomcu.getText().toString(),type_relation.getText().toString()
                                        ,numerocu.getText().toString(),autrenumcu.getText().toString(),groupe.getText().toString(),poids.getText().toString(),allergies.getText().toString()
                                        ,medicaments_regu.getText().toString(),cmpreexistantes.getText().toString(),nom_assureur.getText().toString(),policeNum.getText().toString(),num_securite_social.getText().toString()
                                        ,direction_medicale.getText().toString());
                            }
                        });
                    }
                } else {
                }
            }
        });


        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStep=2;
                dataUser.setVisibility(View.GONE);
                info_urgence.setVisibility(View.VISIBLE);
                info_medical.setVisibility(View.GONE);

                dpbtn.setVisibility(View.GONE);
                cubtn.setVisibility(View.VISIBLE);
                imbtn.setVisibility(View.GONE);
                next.setText("Suivant");
                // Afficher le formulaire correspondant
            }
        });

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStep=3;
                dataUser.setVisibility(View.GONE);
                info_urgence.setVisibility(View.GONE);
                info_medical.setVisibility(View.VISIBLE);

                dpbtn.setVisibility(View.GONE);
                cubtn.setVisibility(View.GONE);
                imbtn.setVisibility(View.VISIBLE);
                next.setText("Terminer");
                // Afficher le formulaire correspondant
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void ajouterLesDonneeesMedicales(String sexe, String dateNaissance, String adress, String medecinNum, String prenomSecu, String nomSecu, String relation, String numeroSecu, String autreNumSecu, String groupe, String poids
            , String allergies, String mediRegulier, String compriPeexistant, String nomAssureur, String policeNumero, String numeroSecuSociale, String dma)
    {
        editor.putString("sexe", sexe);
        editor.putString("dateDeNaissance", dateNaissance);
        editor.putString("numeroDeSecu", numeroSecu);
        editor.putString("prenomcu", prenomSecu);
        editor.putString("nomcu", nomSecu);
        editor.putString("adressVictime", adress);
        editor.putString("typeRelation", relation);
        editor.putString("autreNumcu", autreNumSecu);
        editor.putString("groupe", groupe);
        editor.putString("poids", poids);
        editor.putString("allergies", allergies);
        editor.putString("medicaments", mediRegulier);
        editor.putString("assureurNon", nomAssureur);
        editor.putString("policeNum", policeNumero);
        editor.putString("numSecuriteSocial", numeroSecuSociale);
        editor.putString("cmpreexistantes", compriPeexistant);
        editor.putString("numeroMedecin", medecinNum);
        editor.putString("dma", dma);
        editor.apply();
        DonneesMedicales donnes =new DonneesMedicales(id,nomSecu,prenomSecu,numeroSecu,autreNumSecu,relation,sexe,dateNaissance
                ,adress,groupe,poids,allergies,mediRegulier,nomAssureur,numeroSecuSociale,policeNumero,dma,medecinNum,compriPeexistant);
        databaseReference.child(id).setValue(donnes);
        popusCostum.dismiss();
        startActivity(new Intent(UpdateMedicalData.this, ProfilePanel.class));
        finish();
    }
    @Override
    public void onBackPressed() {
        incr++;
        if (incr==1){
            super.onBackPressed();
            startActivity(new Intent(UpdateMedicalData.this,ProfilePanel.class));
            finish();
        }
    }
}