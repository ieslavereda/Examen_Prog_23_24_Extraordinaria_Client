package com.example.examen_prog_23_24_extraordinaria_client.model;

import com.example.examen_prog_23_24_extraordinaria_client.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Fruta {

    BAR("BAR", R.mipmap.bar),
    CAMPANA("Campana", R.mipmap.campana),
    CEREZA("Cereza", R.mipmap.cereza),
    CIRUELA("Ciruela", R.mipmap.ciruela),
    FRESA("Fresa", R.mipmap.fresa),
    LIMON("Limon", R.mipmap.limon),
    PLATANO("Platano", R.mipmap.platano),
    SIETE("7", R.mipmap.siete),
    TREBOL("Trebol", R.mipmap.trebol);

    private String nombre;
    private int imageResource;

    Fruta(String nombre, int imageResource) {
        this.nombre = nombre;
        this.imageResource = imageResource;
    }

    public static Fruta aleatorio() {
        List<Fruta> frutas = new ArrayList<>(Arrays.asList(Fruta.values()));
        Collections.shuffle(frutas);
        return frutas.get(0);
    }

    public String getNombre() {
        return nombre;
    }

    public int getImageResource() {
        return imageResource;
    }

    public Fruta next() {

        int pos = ordinal() + 1;

        if (pos == Fruta.values().length)
            pos = 0;

        return Fruta.values()[pos];
    }

    public Fruta previous() {

        int pos = ordinal() - 1;

        if (pos < 0)
            pos = Fruta.values().length - 1;

        return Fruta.values()[pos];
    }


}
