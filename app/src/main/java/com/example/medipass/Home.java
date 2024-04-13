package com.example.medipass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Home extends AppCompatActivity {

    Button button;
    TextView q1,rp1,rp2,rp3,rp4,rp5,rp6,rp7;
    ImageView ic1,ic2,ic3,ic4,ic5,ic6,ic7;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int incr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String id = sharedPreferences.getString("id", "");
        String prenom = sharedPreferences.getString("prenom", "");
        String nom = sharedPreferences.getString("nom", "");
        if (!id.isEmpty() || !prenom.isEmpty() || !nom.isEmpty()){
            startActivity(new Intent(Home.this,ProfilePanel.class));
            finish();
        }

        button = findViewById(R.id.button);
        q1 = findViewById(R.id.q1);
        rp1 = findViewById(R.id.rp1);
        rp2 = findViewById(R.id.rp2);
        rp3 = findViewById(R.id.rp3);
        rp4 = findViewById(R.id.rp4);
        rp5 = findViewById(R.id.rp5);
        rp6 = findViewById(R.id.rp6);
        rp7 = findViewById(R.id.rp7);
        ic1 = findViewById(R.id.ic1);
        ic7 = findViewById(R.id.ic7);
        ic2 = findViewById(R.id.ic2);
        ic3 = findViewById(R.id.ic3);
        ic4 = findViewById(R.id.ic4);
        ic5 = findViewById(R.id.ic5);
        ic6 = findViewById(R.id.ic6);

        ic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp1.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp1.setVisibility(View.GONE);
                    ic1.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp1.setVisibility(View.VISIBLE);
                    ic1.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });
        ic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp2.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp2.setVisibility(View.GONE);
                    ic2.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp2.setVisibility(View.VISIBLE);
                    ic2.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });
        ic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp3.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp3.setVisibility(View.GONE);
                    ic3.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp3.setVisibility(View.VISIBLE);
                    ic3.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });
        ic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp4.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp4.setVisibility(View.GONE);
                    ic4.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp4.setVisibility(View.VISIBLE);
                    ic4.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });
        ic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp5.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp5.setVisibility(View.GONE);
                    ic5.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp5.setVisibility(View.VISIBLE);
                    ic5.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });
        ic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp6.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp6.setVisibility(View.GONE);
                    ic6.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp6.setVisibility(View.VISIBLE);
                    ic6.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });
        ic7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifiez si le TextView est actuellement visible ou non
                if (rp7.getVisibility() == View.VISIBLE) {
                    // Le TextView est visible, alors cachez-le et affichez l'ImageView par défaut
                    rp7.setVisibility(View.GONE);
                    ic7.setImageResource(R.drawable.img_10); // Image par défaut
                } else {
                    // Le TextView n'est pas visible, alors affichez-le et changez l'image de l'ImageView
                    rp7.setVisibility(View.VISIBLE);
                    ic7.setImageResource(R.drawable.img_01); // Nouvelle image
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Register.class));
                finish();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
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