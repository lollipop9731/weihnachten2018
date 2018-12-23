package com.example.loren.weihnachten2018;

public class Item {
    int backgroundimage;
    String beschreibung;
    int anzahl;

    public Item() {
    }

    public Item(int backgroundimage, String beschreibung, int anzahl) {
        this.backgroundimage = backgroundimage;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
    }

    public int getBackgroundimage() {
        return backgroundimage;
    }

    public void setBackgroundimage(int backgroundimage) {
        this.backgroundimage = backgroundimage;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
