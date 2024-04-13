package com.example.medipass;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.medipass.models.ModelUserLogin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.medipass", appContext.getPackageName());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        ModelUserLogin user =new ModelUserLogin("01","bamba","tes","test@gmail.com","123","20/03/2024");
        String userId = databaseReference.child("users").push().getKey(); // Génère une clé unique pour chaque utilisateur
        databaseReference.child("users").child(userId).setValue(user);

    }
}