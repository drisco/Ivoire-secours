package com.example.medipass;

import android.app.Activity;
import android.app.Dialog;

public class PopupRegister extends Dialog {
    public PopupRegister(Activity activity){
        super(activity, androidx.appcompat.R.style.Base_Theme_AppCompat_Dialog_Alert);
        setContentView(R.layout.popup_register);

    }
}
