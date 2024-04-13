package com.example.medipass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    DatabaseReference databaseReference;

    TextView profil,session,sauver,annuler;
    EditText numero,otherNum,pays,ville,quartier,adresse;
    CircleImageView userprofile;
    String userId;
    PopupRegister popusCostum;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 2;
    private static final int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 20;

    private Uri selectedImageUri;
    String image;
    int incr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

         userId = sharedPreferences.getString("id", "");
        String prenom = sharedPreferences.getString("prenom", "");


        profil =findViewById(R.id.profil);
        session =findViewById(R.id.session);
        session.setText("Bonjour "+prenom);
        sauver =findViewById(R.id.text1);
        annuler =findViewById(R.id.text2);
        userprofile =findViewById(R.id.userprofile);

        numero =findViewById(R.id.numero);
        otherNum =findViewById(R.id.othernum);
        pays =findViewById(R.id.state);
        ville =findViewById(R.id.city);
        quartier =findViewById(R.id.quart);
        adresse =findViewById(R.id.adr);

        sauver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userprofile.getDrawable() == null) {
                    // L'ImageView est vide
                } else {
                     image=ImageUtils.imageViewToString(userprofile);
                }
                if (numero.getText().toString().isEmpty()||otherNum.getText().toString().isEmpty()||pays.getText().toString().isEmpty()
                        ||ville.getText().toString().isEmpty()||quartier.getText().toString().isEmpty()){
                    Toast.makeText(Profile.this, "Veuillez verifier vos champs", Toast.LENGTH_SHORT).show();
                }else{
                    popusCostum = new PopupRegister(Profile.this);
                    popusCostum.setCancelable(false);
                    popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    popusCostum.show();
                    AddInfos(numero.getText().toString(),otherNum.getText().toString(),pays.getText().toString(),ville.getText().toString(),
                            quartier.getText().toString(),adresse.getText().toString());
                }
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog();
            }
        });
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Profile.this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
        ImageView cameraOption = bottomSheetDialog.findViewById(R.id.cameraOption);
        if (cameraOption != null) {
            cameraOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Vérifier la permission CAMERA
                    if (ContextCompat.checkSelfPermission(Profile.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // La permission CAMERA n'est pas accordée, demandez-la à l'utilisateur
                        ActivityCompat.requestPermissions(Profile.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                    } else {
                        // La permission CAMERA est accordée, ouvrir l'appareil photo
                        openCamera();
                    }
                    bottomSheetDialog.dismiss();
                }
            });
        }

        ImageView galleryOption = bottomSheetDialog.findViewById(R.id.galleryOption);
        if (galleryOption != null) {
            galleryOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Vérifier la permission READ_EXTERNAL_STORAGE pour accéder à la galerie
                    if (ContextCompat.checkSelfPermission(Profile.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        // La permission READ_EXTERNAL_STORAGE n'est pas accordée, demandez-la à l'utilisateur
                        ActivityCompat.requestPermissions(Profile.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
                    } else {
                        // La permission READ_EXTERNAL_STORAGE est accordée, ouvrir la galerie
                        openGallery();
                    }
                    bottomSheetDialog.dismiss();
                }
            });
        }

        bottomSheetDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE: {
                // Vérifier si la demande de permission a été accordée
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission CAMERA a été accordée, ouvrir l'appareil photo
                    openCamera();
                } else {
                    // La permission CAMERA a été refusée, afficher un message d'erreur ou demander à nouveau la permission
                    // Vous pouvez également désactiver les fonctionnalités liées à la caméra ici
                    Toast.makeText(this, "Permission CAMERA refusée", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE: {
                // Vérifier si la demande de permission a été accordée
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // La permission READ_EXTERNAL_STORAGE a été accordée, ouvrir la galerie
                    openGallery();
                } else {
                    // La permission READ_EXTERNAL_STORAGE a été refusée, afficher un message d'erreur ou demander à nouveau la permission
                    // Vous pouvez également désactiver les fonctionnalités liées à la galerie ici
                    Toast.makeText(this, "Permission READ_EXTERNAL_STORAGE refusée", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:
                break;
        }
    }

    public void openGallery() {
        // Intent pour ouvrir la galerie
        Intent pickPhotoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_PICK);
    }

    public void openCamera() {
        // Intent pour ouvrir l'appareil photo
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Vérifier si l'autorisation CAMERA a été accordée
                    // Image capturée depuis l'appareil photo
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    userprofile.setImageBitmap(imageBitmap);

            } else if (requestCode == REQUEST_IMAGE_PICK) {
                // Vérifier si l'autorisation READ_EXTERNAL_STORAGE a été accordée
                    // Image sélectionnée depuis la galerie
                    if (data != null) {
                        selectedImageUri = data.getData();
                        userprofile.setImageURI(selectedImageUri);
                    }

            }
        }
    }


    private void AddInfos(String num, String othernum, String pays, String ville, String quartier, String adress) {
        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("numero", num);
        updateUser.put("otherNumero", othernum);
        updateUser.put("pays", pays);
        updateUser.put("ville", ville);
        updateUser.put("quartier", quartier);
        updateUser.put("adress", adress);
        updateUser.put("photo", image);
        databaseReference.child(userId).updateChildren(updateUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Mettre à jour réussie
                        editor.putString("numero", num);
                        editor.putString("otherNume", othernum);
                        editor.putString("pays", pays);
                        editor.putString("ville", ville);
                        editor.putString("quartier", quartier);
                        editor.putString("adress", adress);
                        editor.putString("image", image);
                        editor.apply();
                        popusCostum.dismiss();
                        startActivity(new Intent(Profile.this,ProfilePanel.class));
                        finish();                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Erreur lors de la mise à jour
                    }
                });

    }
    @Override
    public void onBackPressed() {
        incr++;
        if (incr==1){
            super.onBackPressed();
            startActivity(new Intent(Profile.this,RegisterInfo.class));
            finish();
        }
    }
}