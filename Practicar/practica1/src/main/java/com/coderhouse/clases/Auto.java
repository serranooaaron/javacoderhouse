package com.coderhouse.clases;

public class Auto {
    private String tipo;
    private String marca;
    private int ruedas;

    public Auto() {
    }

    public Auto(String tipo, String marca, int ruedas) {
        this.setTipo(tipo);
        this.setMarca(marca);
        this.setRuedas(ruedas);
    }

    // GETTERS
    public int getRuedas() {
        return ruedas;
    }


    public String getMarca() {
        return marca;
    }


    public String getTipo() {
        return tipo;
    }

    // SETTERS
    public void setTipo(String tipo) {
        if (tipo != null && !tipo.isEmpty()) this.tipo = tipo;
    }

    public void setMarca(String marca) {
        if (marca != null && !marca.isEmpty()) this.marca = marca;
    }

    public void setRuedas(int ruedas) {
        if (ruedas <= 0) {
            throw new IllegalArgumentException("❌ No puede ser un vehiculo con < 0 ruedas");
        }
        this.ruedas = ruedas;

    }


    @Override
    public String toString() {
        return "Auto{" +
                "tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", ruedas=" + ruedas +
                '}';
    }


}
