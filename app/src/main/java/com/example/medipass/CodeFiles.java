package com.example.medipass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class CodeFiles extends AppCompatActivity {
    int incr;
    ImageView qrImageView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CircleImageView userdashbord;
    TextView session;
    PopupRegister popusCostum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_codefiles);
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String photo = sharedPreferences.getString("image", "");
        popusCostum = new PopupRegister(CodeFiles.this);
        popusCostum.setCancelable(false);
        popusCostum.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popusCostum.show();
        String randomText = generateRandomText();

        Bitmap bitmap = generateQRCode(randomText);

        qrImageView =findViewById(R.id.imageView2);
        qrImageView.setImageBitmap(bitmap);
        popusCostum.dismiss();
        session =findViewById(R.id.session);
        userdashbord =findViewById(R.id.userdashbord);
        if (photo.isEmpty()){
            userdashbord.setImageResource(R.drawable.img_5);
        }else{
            Bitmap map =ImageUtils.stringToBitmap(photo);
            userdashbord.setImageBitmap(map);
        }

        session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear(); // Supprimer toutes les données
                editor.apply();
                startActivity(new Intent(CodeFiles.this, Login.class));
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private Bitmap generateQRCode(String text) {
        Bitmap bitmap = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcode = new BarcodeEncoder();
            bitmap =barcode.createBitmap(bitMatrix);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private String generateRandomText() {
        // Définir la longueur du texte aléatoire
        int length = 20;
        // Chars possibles pour le texte aléatoire
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        // Générer un texte aléatoire
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            builder.append(chars.charAt(index));
        }
        return builder.toString();
    }
    @Override
    public void onBackPressed() {
        incr++;
        if (incr==1){
            super.onBackPressed();
            startActivity(new Intent(CodeFiles.this,ProfilePanel.class));
            finish();
        }
    }
}