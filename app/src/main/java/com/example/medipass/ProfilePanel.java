package com.example.medipass;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePanel extends AppCompatActivity {

    ImageView dash,profil;
    CircleImageView userdashbord,userphoto;
    TextView deconnexion,username;

    TextView session;
    RelativeLayout editdata;
    LinearLayout addinfos,scanadmin,scan1,qrcodegene;

    TextView nomCompletTextView;
    TextView sexeTextView;
    int incr;
    TextView dateNaissanceTextView,pdf;
    PopupRegister popusCostum;
    // Déclarez les autres TextViews de votre mise en page ici
    TextView prenomNomAngeTextView,relationAngePatientTextView,adresseResidenceAngeTextView,ecoleUniversiteInstitutTextView
    ,contactMd,groupeSanguinTextView,poidsTextView,allergiesTextView ,medicamentsTextView,nomAssureurTextView,numPoliceAssuranceTextView
            ,medicamentsPrisTextView,directivesMedicalesAntenpeesTextView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ScrollView infoFile;
    LinearLayout message;
    private static final int PERMISSION_REQUEST_CODE = 140;
    private static final int PERMISSION_REQUEST_CO = 10;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_panel);
        if (checkPermissionBoolean()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();

        }

        userdashbord = findViewById(R.id.userdashbord);
        scanadmin = findViewById(R.id.scanadmin);
        qrcodegene = findViewById(R.id.qrcodegene);
        scan1 = findViewById(R.id.scan1);
        pdf = findViewById(R.id.pdf);
        editdata = findViewById(R.id.editdata);
        dash = findViewById(R.id.dash);
        deconnexion = findViewById(R.id.session);
        userphoto = findViewById(R.id.userphoto);
        username = findViewById(R.id.username);
        profil = findViewById(R.id.profil);
        addinfos = findViewById(R.id.addinfos);
        contactMd = findViewById(R.id.contactM);
        ImageView clickmoi = findViewById(R.id.clickmoi);
        message = findViewById(R.id.message);
        infoFile = findViewById(R.id.infoFile);

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String prenom = sharedPreferences.getString("prenom", "");
        String nom = sharedPreferences.getString("nom", "");
        String photo = sharedPreferences.getString("image", "");
        String id = sharedPreferences.getString("id", "");

        String numeroMedecin = sharedPreferences.getString("numeroMedecin", "");
        contactMd.setText(numeroMedecin);
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


        if (!prenom.isEmpty()|| !nom.isEmpty()){
            if (prenom.equals("Médipass") && nom.equals("Admin")){
                addinfos.setVisibility(View.GONE);
                infoFile.setVisibility(View.GONE);
                message.setVisibility(View.GONE);
                scanadmin.setVisibility(View.VISIBLE);
            }else{
                /*scanadmin.setVisibility(View.GONE);
                message.setVisibility(View.VISIBLE);
                addinfos.setVisibility(View.GONE);*/
                if (nomcu1.isEmpty()||prenomcu1.isEmpty()){
                    infoFile.setVisibility(View.GONE);
                    message.setVisibility(View.VISIBLE);
                    addinfos.setVisibility(View.VISIBLE);
                    scanadmin.setVisibility(View.GONE);
                }else{
                    message.setVisibility(View.GONE);
                    infoFile.setVisibility(View.VISIBLE);
                    scanadmin.setVisibility(View.GONE);
                }
            }
        }

        methodSetText(prenom,nom,photo);

        nomCompletTextView = findViewById(R.id.nom_complet);
        nomCompletTextView.setText(nom+" "+prenom);
        sexeTextView = findViewById(R.id.sexe);
        sexeTextView.setText(sexe1);
        dateNaissanceTextView = findViewById(R.id.date_naissance);
        dateNaissanceTextView.setText(dateDeNaissance1);
        // Initialisez les autres TextViews de votre mise en page ici
        prenomNomAngeTextView = findViewById(R.id.prenom_nom_ange);
        prenomNomAngeTextView.setText(prenomcu1+" "+nomcu1);
        relationAngePatientTextView = findViewById(R.id.relation_ange_patient);
        relationAngePatientTextView.setText(typeRelation1);
        adresseResidenceAngeTextView = findViewById(R.id.adresse_residence_ange);
        adresseResidenceAngeTextView.setText(numeroDeSecu1);
        ecoleUniversiteInstitutTextView = findViewById(R.id.ecole_universite_institut);
        ecoleUniversiteInstitutTextView.setText(autreNumcu1);
        groupeSanguinTextView = findViewById(R.id.groupe_sanguin);
        groupeSanguinTextView.setText(groupe1);
        poidsTextView = findViewById(R.id.poids);
        poidsTextView.setText(poids1);
        allergiesTextView = findViewById(R.id.allergies);
        allergiesTextView.setText(allergies1);
        medicamentsTextView = findViewById(R.id.medicaments);
        medicamentsTextView.setText(medicaments1);

        nomAssureurTextView = findViewById(R.id.nom_assureur);
        nomAssureurTextView.setText(assureurNon1);
        numPoliceAssuranceTextView = findViewById(R.id.num_police_assurance);
        numPoliceAssuranceTextView.setText(policeNum1);

        medicamentsPrisTextView.setText(numSecuriteSocial1);
        directivesMedicalesAntenpeesTextView = findViewById(R.id.directives_medicales_antenpees);
        directivesMedicalesAntenpeesTextView.setText(dma1);



        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(ProfilePanel.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                if (prenomcu1.isEmpty()||sexe1.isEmpty()){
                    popusCostum.dismiss();
                    Toast.makeText(ProfilePanel.this, "Vous n'avez aucune données medicales", Toast.LENGTH_SHORT).show();
                }else{
                    convertToPdfAndSend();
                }
            }
        });
        editdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(ProfilePanel.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                startActivity(new Intent(ProfilePanel.this, UpdateMedicalData.class));
                finish();
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePanel.this, ProfileUser.class));
                finish();
            }
        });

        qrcodegene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(ProfilePanel.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                startActivity(new Intent(ProfilePanel.this, CodeFiles.class));
                finish();

            }
        });

        clickmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePanel.this, MedicalFiles.class));
                finish();
            }
        });

        userdashbord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilePanel.this, ProfilePanel.class));
                finish();
            }
        });

        deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(ProfilePanel.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                editor.clear(); // Supprimer toutes les données
                editor.apply();
                startActivity(new Intent(ProfilePanel.this, Login.class));
                finish();
            }
        });

        scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(ProfilePanel.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                ScanOptions options = new ScanOptions();
                options.setPrompt("Medipass scanne");
                options.setOrientationLocked(false);
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
                options.setBeepEnabled(true);
                options.setCaptureActivity(Capture.class);
                barCode.launch(options);

            }
        });

        addinfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popusCostum = new PopupRegister(ProfilePanel.this);
                popusCostum.setCancelable(false);
                popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popusCostum.show();
                if (prenomcu1.isEmpty()||sexe1.isEmpty()){
                    popusCostum.dismiss();
                    startActivity(new Intent(ProfilePanel.this, MedicalFiles.class));
                    finish();
                }else{
                    popusCostum.dismiss();
                    startActivity(new Intent(ProfilePanel.this, UpdateMedicalData.class));
                    finish();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }



    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CO);
    }

    private boolean checkPermissionBoolean() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }
    private void convertToPdfAndSend() {

        String filename= System.currentTimeMillis()+ "medicalFile.pdf";
        String pdfFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/medipass/"+filename;
        File pdfFile = new File(pdfFilePath);

        if (pdfFile.exists()) {
            // Supprimer le fichier PDF précédent s'il existe
            boolean deleted = pdfFile.delete();
            if (!deleted) {
                popusCostum.dismiss();
                // Gestion de l'échec de la suppression du fichier
                Toast.makeText(this, "Erreur lors de la suppression du fichier PDF précédent", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Créer un nouveau document PDF
        PdfDocument document = new PdfDocument();

        // Créer une page PDF
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1080, 1920, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

// Obtenir la vue à dessiner
        View view = findViewById(R.id.l8);

// Mesurer la vue
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(canvas.getWidth(), View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(canvas.getHeight(), View.MeasureSpec.EXACTLY);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        int marginLeft = 50; // Marge à gauche
        int marginTop = 10; // Marge en haut

// Calculer la position horizontale pour centrer la vue avec marges
        int centerX = (canvas.getWidth() - view.getMeasuredWidth()) / 2 + marginLeft;
        int centerY = (canvas.getHeight() - view.getMeasuredHeight()) / 2 + marginTop;


// Définir les limites de dessin pour la vue centrée
        view.layout(centerX, centerY, centerX + view.getMeasuredWidth(), centerY + view.getMeasuredHeight());

// Dessiner la vue sur le canvas
        view.draw(canvas);

// Terminer la page
        document.finishPage(page);


        // Enregistrer le document PDF localement
        String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/medipass/";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                // Gestion de l'échec de création du répertoire
                Toast.makeText(this, "Erreur lors de la création du répertoire", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        pdfFile = new File(directoryPath + filename);

        try {
            FileOutputStream outputStream = new FileOutputStream(pdfFile);
            document.writeTo(outputStream);
            document.close();
            outputStream.flush();
            outputStream.close();
            popusCostum.dismiss();
            Toast.makeText(this, "Fichier PDF enregistré avec succès", Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            e.printStackTrace();
            // Gestion de l'erreur d'écriture du fichier PDF
            popusCostum.dismiss();
            Toast.makeText(this, "Erreur lors de la génération du fichier PDF"+e, Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void methodSetText(String prenom, String nom, String photo) {
        username.setText("Bonjour "+nom+" " +prenom+" Bienvenue sur Medipass");
        if (photo.isEmpty()){
            userphoto.setImageResource(R.drawable.img_5);
            userdashbord.setImageResource(R.drawable.img_5);
        }else{
            Bitmap map =ImageUtils.stringToBitmap(photo);
            userphoto.setImageBitmap(map);
            userdashbord.setImageBitmap(map);
        }
    }

    ActivityResultLauncher<ScanOptions> barCode= registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() !=null){
            Toast.makeText(this, "Contenu du code QR : " + result.getContents(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PdfFile.class);
            intent.putExtra("idQrcode", result.getContents());
            startActivity(intent);
            popusCostum.dismiss();
            finish();
        }else{
            popusCostum.dismiss();
        }
    });

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