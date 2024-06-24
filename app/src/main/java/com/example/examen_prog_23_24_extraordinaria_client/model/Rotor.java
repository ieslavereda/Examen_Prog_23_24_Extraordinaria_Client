package com.example.examen_prog_23_24_extraordinaria_client.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.examen_prog_23_24_extraordinaria_client.R;

public class Rotor extends ConstraintLayout {

    private Tragaperras tragaperras;
    private ImageView ivUp;
    private ImageView ivDown;
    private ImageView ivFruta;

    private Fruta fruta;

    public Rotor(@NonNull Context context) {
        super(context);
        init();
    }

    public Rotor(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Rotor(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Rotor(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.rotor,this,true);

        ivUp = view.findViewById(R.id.ivUp);
        ivDown = view.findViewById(R.id.ivDown);
        ivFruta = view.findViewById(R.id.ivFruta);

        ivUp.setOnClickListener(v->{
            fruta = fruta.next();
            tragaperras.gastarAvance();
            actualizar();
        });

        ivDown.setOnClickListener(v->{
            fruta=fruta.previous();
            tragaperras.gastarAvance();
            actualizar();
        });

        girarAleatoriamente();
        setEnableButtons(false);
    }

    public void girarAleatoriamente(){
        fruta = Fruta.aleatorio();
        actualizar();
    }

    public void actualizar(){
        ivFruta.setImageResource(fruta.getImageResource());
    }

    public Fruta getFruta() {
        return fruta;
    }

    public void setEnableButtons(boolean enabled){
        ivUp.setEnabled(enabled);
        ivDown.setEnabled(enabled);
    }

    public void setTragaperras(Tragaperras tragaperras){
        this.tragaperras = tragaperras;
    }

}
