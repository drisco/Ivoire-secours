package com.example.medipass;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class Register extends AppCompatActivity {

    RelativeLayout btn2, btn1;
    LinearLayout scane;

    private static final int RC_SIGN_IN = 123;
    int incr;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        btn2= findViewById(R.id.btn2);
        scane= findViewById(R.id.scane);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
                finish();
            }
        });
        scane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScanOptions options = new ScanOptions();
                options.setPrompt("Medipass scanne");
                options.setOrientationLocked(false);
                options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
                options.setBeepEnabled(true);
                options.setCaptureActivity(Capture.class);
                barCode.launch(options);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ActivityResultLauncher<ScanOptions> barCode= registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() !=null){
            Toast.makeText(this, "Contenu du code QR : " + result.getContents(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, RegisterInfo.class);
            intent.putExtra("idQrcode", result.getContents());
            startActivity(intent);
            finish();
        }else{
        }
    });
  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                // Le scan a été annulé
                Toast.makeText(this, "Scan annulé", Toast.LENGTH_SHORT).show();
            } else {
                // Le scan a réussi, result.getContents() contient la valeur du code QR
                String scannedData = result.getContents();
                Toast.makeText(this, "Contenu du code QR : " + scannedData, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, RegisterInfo.class);
                intent.putExtra("idQrcode", scannedData);
                startActivity(intent);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/
    @Override
    public void onBackPressed() {
        incr++;
        if (incr==1){
            super.onBackPressed();
            startActivity(new Intent(Register.this,Home.class));
            finish();
        }
    }
}