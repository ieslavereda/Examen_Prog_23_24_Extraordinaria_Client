package com.example.examen_prog_23_24_extraordinaria_client.model;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.examen_prog_23_24_extraordinaria_client.R;

import java.util.ArrayList;
import java.util.List;

public class Tragaperras extends ConstraintLayout implements MaquinaAzar {

    private static final float SALDO_INICIAL = 5000;

    private float saldoInicial;
    private float saldo;
    private int creditos;
    private int avances;
    private boolean premioRetirado;
    private Button btnJugar;
    private Button btnPremio;
    private TextView tvCreditos;
    private TextView tvAvances;
    private List<Rotor> valores;

    public Tragaperras(@NonNull Context context) {
        super(context);
        init();
    }

    public Tragaperras(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Tragaperras(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Tragaperras(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tragaperras, this, true);

        this.saldoInicial = SALDO_INICIAL;
        this.creditos = 0;
        premioRetirado = true;

        btnJugar = view.findViewById(R.id.btnJugar);
        btnPremio = view.findViewById(R.id.btnPremio);
        tvCreditos = view.findViewById(R.id.tvCreditos);
        tvAvances = view.findViewById(R.id.tvAvances);

        valores = new ArrayList<>();
        valores.add(findViewById(R.id.rotor1));
        valores.add(findViewById(R.id.rotor2));
        valores.add(findViewById(R.id.rotor3));

        for (Rotor r : valores)
            r.setTragaperras(this);

        btnJugar.setOnClickListener(v -> {
            if (creditos > 0) {
                premioRetirado = false;
                avances = 2;
                habilitarAvances(true);
                creditos--;
                for (Rotor rotor : valores)
                    rotor.girarAleatoriamente();
                tvCreditos.setText(String.valueOf(creditos));
            } else
                Toast.makeText(getContext(), "Tienes que introducir una moneda", Toast.LENGTH_LONG).show();
        });

    }

    public void recogerPremioListener(View.OnClickListener listener) {
        btnPremio.setOnClickListener(listener);
    }

    public float getSaldoInicial() {
        return saldoInicial;
    }

    public float getBeneficio() {
        return saldo - saldoInicial;
    }

    @Override
    public void introducirMoneda() {
        saldo++;
        creditos++;
        tvCreditos.setText(String.valueOf(creditos));
    }

    @Override
    public float retirarPremio() {
        if (!premioRetirado) {

            premioRetirado = true;
            float premio = comprobarPremio();

            if(premio<=saldo) {
                saldo -= premio;
                Toast.makeText(getContext(), "Has obtenido " + premio + "€", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Has obtenido " + premio + "€, pero solo puedo entregarte " + saldo +"€", Toast.LENGTH_LONG).show();
                premio = saldo;
                saldo=0;
            }

            return premio;
        } else
            return 0;
    }

    private float comprobarPremio() {

        if (contar(Fruta.SIETE) == 3)
            return 50;

        if (contar(Fruta.BAR) == 3)
            return 25;

        for (Fruta fruta : Fruta.values())
            if (contar(fruta) == 3)
                return 10;

        for (Fruta fruta : Fruta.values())
            if (contar(fruta) == 2)
                return 5;

        return 0;
    }

    private int contar(Fruta fruta) {
        return (int) valores.stream()
                .map(rotor -> rotor.getFruta())
                .filter(f -> f.equals(fruta))
                .count();
    }

    public void gastarAvance() {
        avances--;
        tvAvances.setText(""+avances);
        if (avances == 0)
            habilitarAvances(false);
    }

    private void habilitarAvances(boolean enable) {
        tvAvances.setText(""+avances);
        for (Rotor r : valores)
            r.setEnableButtons(enable);
    }


}
