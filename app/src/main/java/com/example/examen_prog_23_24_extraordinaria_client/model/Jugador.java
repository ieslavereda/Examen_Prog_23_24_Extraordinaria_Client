package com.example.examen_prog_23_24_extraordinaria_client.model;

public class Jugador {
    private String nombre;
    private float saldo;

    public Jugador(String nombre, float saldo) {
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void recogePremio(float cantidad) {
        this.saldo += cantidad;
    }

    public boolean gastarMoneda() {
        if (saldo >= 1) {
            saldo -= 1;
            return true;
        }
        return false;
    }

}
