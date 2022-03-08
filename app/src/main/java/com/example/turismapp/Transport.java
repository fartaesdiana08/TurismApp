package com.example.turismapp;

import java.io.Serializable;

public class Transport implements Serializable {
    private String firma;
    private String nrTelefon;
    private int nrTaxi;

    public Transport(String firma, String nrTelefon, int nrTaxi) {
        this.firma = firma;
        this.nrTelefon = nrTelefon;
        this.nrTaxi = nrTaxi;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public int getNrTaxi() {
        return nrTaxi;
    }

    public void setNrTaxi(int nrTaxi) {
        this.nrTaxi = nrTaxi;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "firma='" + firma + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", nrTaxi=" + nrTaxi +
                '}';
    }
}
