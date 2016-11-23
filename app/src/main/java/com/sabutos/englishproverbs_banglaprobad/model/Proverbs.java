package com.sabutos.englishproverbs_banglaprobad.model;

/**
 * Created by devil on 15-Oct-16.
 */

public class Proverbs {
    private int id;
    private String proverbs;
    private String  probad;
    private int flag;

    public Proverbs(int id, String proverbs, String probad, int flag) {
        this.id = id;
        this.proverbs = proverbs;
        this.probad = probad;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProverbs() {
        return proverbs;
    }

    public void setProverbs(String proverbs) {
        this.proverbs = proverbs;
    }

    public String getProbad() {
        return probad;
    }

    public void setProbad(String probad) {
        this.probad = probad;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
