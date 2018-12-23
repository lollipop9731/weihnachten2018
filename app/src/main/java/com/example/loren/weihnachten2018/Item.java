package com.example.loren.weihnachten2018;

import android.graphics.drawable.Drawable;

public class Item {
    Drawable backgroundimage;
    String beschreibung;
    int anzahl;
    String title;

    public Item() {
    }

    public Item(Drawable backgroundimage, String beschreibung, int anzahl) {
        this.backgroundimage = backgroundimage;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
    }

    public Item(Drawable backgroundimage, String beschreibung, int anzahl, String title) {
        this.backgroundimage = backgroundimage;
        this.beschreibung = beschreibung;
        this.anzahl = anzahl;
        this.title = title;
    }

    public Drawable getBackgroundimage() {
        return backgroundimage;
    }

    public void setBackgroundimage(Drawable backgroundimage) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
