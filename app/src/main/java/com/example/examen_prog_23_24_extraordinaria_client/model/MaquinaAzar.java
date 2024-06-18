package com.example.examen_prog_23_24_extraordinaria_client.model;

import android.view.View;

public interface MaquinaAzar {

    void introducirMoneda();
    float retirarPremio();
    void recogerPremioListener(View.OnClickListener listener);

}
