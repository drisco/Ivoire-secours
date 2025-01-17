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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.medipass.models.DonneesMedicales;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MedicalFiles extends AppCompatActivity {
    ScrollView info_urgence,info_medical,dataUser;
    LinearLayout dp,cu,im;
    int incr;
    private RelativeLayout annulationbtn,sauvegardebtn;
    DatabaseReference databaseReference;


    EditText nometprenom,sexe,date_de_nai,adresse,numero,numeroMeddecin,numero_de_secu,prenomcu,nomcu,type_relation
            ,numerocu,autrenumcu,doctorName,doctorNum,groupe,poids,aliments,allergies
              ,medicaments_regu,cmpreexistantes,nom_assureur,policeNum,num_securite_social,direction_medicale;


    TextView session1,dpt,cut,imt,next,deconnecter;
    ImageView dpbtn,cubtn,imbtn,mon_profil,acceuil;
    CircleImageView photo1,photo;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int currentStep = 1;
    String id;
    PopupRegister popusCostum;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medical_files);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("donneesmedicale");

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
         id = sharedPreferences.getString("id", "");
        String prenom = sharedPreferences.getString("prenom", "");
        String nom = sharedPreferences.getString("nom", "");
        String photos = sharedPreferences.getString("image", "");
        String numero1 = sharedPreferences.getString("numero", "");
        String adress = sharedPreferences.getString("adress", "");

        photo1 = findViewById(R.id.userdashbord);
        photo = findViewById(R.id.user);
        acceuil = findViewById(R.id.acceuil);
        session1 = findViewById(R.id.username);
        deconnecter = findViewById(R.id.session);
        session1.setText("Bonjour "+nom+ " " + prenom);
        Bitmap bip =ImageUtils.stringToBitmap(photos);
        photo.setImageBitmap(bip);
        photo1.setImageBitmap(bip);
        deconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear(); // Supprimer toutes les données
                editor.apply();
                startActivity(new Intent(MedicalFiles.this, Login.class));
                finish();
            }
        });
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicalFiles.this, ProfilePanel.class));
                finish();
            }
        });

        // scrollview1
        nometprenom = findViewById(R.id.smDu);
        sexe = findViewById(R.id.sexeDu);
        date_de_nai = findViewById(R.id.hbdDu);
        adresse = findViewById(R.id.adresseDu);
        numero = findViewById(R.id.numeroDu);
        numeroMeddecin = findViewById(R.id.autrenumDu);

        setTextMethodePre(adress,nom,prenom,numero1);

        //scrollview2
        prenomcu=findViewById(R.id.prenomCu);
        nomcu=findViewById(R.id.nomCu);
        type_relation=findViewById(R.id.relationCu);
        numerocu = findViewById(R.id.telCu);
        autrenumcu = findViewById(R.id.autrenumCu);


        //Scrollview3
        groupe=findViewById(R.id.sangim);
        poids=findViewById(R.id.poids);
        allergies=findViewById(R.id.allergieim);
        medicaments_regu=findViewById(R.id.medocim);
        cmpreexistantes=findViewById(R.id.chirugieim);
        nom_assureur=findViewById(R.id.assuranceim);
        policeNum=findViewById(R.id.num_assurance_im);
        num_securite_social=findViewById(R.id.num_securite_social);
        direction_medicale=findViewById(R.id.dmaIm);

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
        /*dpd =findViewById(R.id.dpd);
        cuc =findViewById(R.id.cuc);
        imi =findViewById(R.id.imi);*/
        dpbtn.setVisibility(View.VISIBLE);
        cubtn.setVisibility(View.GONE);
        imbtn.setVisibility(View.GONE);

        annulationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicalFiles.this, ProfilePanel.class));
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
                                popusCostum = new PopupRegister(MedicalFiles.this);
                                popusCostum.setCancelable(false);
                                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                popusCostum.show();
                                if (sexe.getText().toString().isEmpty()|| date_de_nai.getText().toString().isEmpty()|| adresse.getText().toString().isEmpty()||prenomcu.getText().toString().isEmpty()||nomcu.getText().toString().isEmpty()||
                                        numerocu.getText().toString().isEmpty()||type_relation.getText().toString().isEmpty()||medicaments_regu.getText().toString().isEmpty()||nom_assureur.getText().toString().isEmpty()){
                                    Toast.makeText(MedicalFiles.this, "Veuillez remplir tout les champs !!", Toast.LENGTH_SHORT).show();
                                }else{
                                    ajouterLesDonneeesMedicales(sexe.getText().toString(),date_de_nai.getText().toString(),adresse.getText().toString(),prenomcu.getText().toString()
                                            ,nomcu.getText().toString(),numerocu.getText().toString(),type_relation.getText().toString(),autrenumcu.getText().toString(),groupe.getText().toString()
                                            ,poids.getText().toString(),allergies.getText().toString(),medicaments_regu.getText().toString(),cmpreexistantes.getText().toString(),nom_assureur.getText().toString()
                                            ,policeNum.getText().toString(),num_securite_social.getText().toString(),direction_medicale.getText().toString(),numeroMeddecin.getText().toString());
                                }

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



    private void ajouterLesDonneeesMedicales(String sexe, String dateNaissance, String adress, String prenomSecu, String nomSecu, String numeroSecu
            , String relation, String autreNumSecu, String groupeSanguin, String poids, String allergies, String MediRegulier, String cmpreexistantes
            , String nomAssureur, String policeNum, String numSecurSociale, String directionMedi, String numeroMedecin) {

        editor.putString("sexe", sexe);
        editor.putString("dateDeNaissance", dateNaissance);
        editor.putString("numeroDeSecu", numeroSecu);
        editor.putString("prenomcu", prenomSecu);
        editor.putString("nomcu", nomSecu);
        editor.putString("adressVictime", adress);
        editor.putString("typeRelation", relation);
        editor.putString("autreNumcu", autreNumSecu);
        editor.putString("groupe", groupeSanguin);
        editor.putString("poids", poids);
        editor.putString("allergies", allergies);
        editor.putString("medicaments", MediRegulier);
        editor.putString("assureurNon", nomAssureur);
        editor.putString("policeNum", policeNum);
        editor.putString("numSecuriteSocial", numSecurSociale);
        editor.putString("cmpreexistantes", cmpreexistantes);
        editor.putString("numeroMedecin", numeroMedecin);
        editor.putString("dma", directionMedi);
        editor.apply();
        DonneesMedicales donnes =new DonneesMedicales(id,nomSecu,prenomSecu,numeroSecu,autreNumSecu,relation,sexe,dateNaissance
                ,adress,groupeSanguin,poids,allergies,MediRegulier,nomAssureur,numSecurSociale,policeNum,directionMedi,numeroMedecin,cmpreexistantes);
        databaseReference.child(id).setValue(donnes);
        popusCostum.dismiss();
        startActivity(new Intent(MedicalFiles.this, ProfilePanel.class));
        finish();

    }

    @SuppressLint("SetTextI18n")
    private void setTextMethodePre(String adress, String nom, String prenom, String numero1) {
        adresse.setText(adress);
        nometprenom.setText(nom+ " " +prenom);
        numero.setText(numero1);
    }
    @Override
    public void onBackPressed() {
        incr++;
        if (incr==1){
            super.onBackPressed();
            startActivity(new Intent(MedicalFiles.this,ProfilePanel.class));
            finish();
        }
    }
}