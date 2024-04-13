package com.example.medipass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class ImageUtils {

    // Méthode pour convertir une image bitmap en une chaîne encodée Base64
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static String imageViewToString(ImageView imageView) {
        // Récupérer le bitmap de l'imageView
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        // Convertir le bitmap en tableau de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();

        // Convertir le tableau de bytes en une chaîne Base64
        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        return encodedImage;
    }

    public static void stringToImageView(String imageString, ImageView imageView) {
        // Décoder la chaîne Base64 en un tableau de bytes
        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);

        // Convertir le tableau de bytes en Bitmap
        Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        // Afficher le Bitmap dans l'ImageView
        imageView.setImageBitmap(decodedBitmap);
    }

    // Méthode pour convertir une chaîne Base64 en Bitmap
    public static Bitmap stringToBitmap(String encodedString) {
        byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}

