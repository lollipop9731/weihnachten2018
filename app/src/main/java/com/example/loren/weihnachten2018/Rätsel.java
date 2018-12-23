package com.example.loren.weihnachten2018;

public class Rätsel {
    String correct;
    String[] possibilites;
    String title;
    boolean solved;

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Rätsel(String correct, String[] possibilites, String title) {

        this.correct = correct;
        this.possibilites = possibilites;
        this.title = title;
        this.solved = false;
    }

    public Rätsel() {
    }

    public int size(){
        return this.possibilites.length;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String[] getPossibilites() {
        return possibilites;
    }

    public void setPossibilites(String[] possibilites) {
        this.possibilites = possibilites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
