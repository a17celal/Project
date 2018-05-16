package com.example.a17celal.project;

/**
 * Created by a17celal on 2018-05-16.
 */

public class Tuggummi {
    private String ID;
    private String name;
    private String company;
    private String location;
    private String category;
    private int pris;

    Tuggummi(String i, String n, String c, String l, String k, int p) {
        ID=i;
        name=n;
        company=c;
        location=l;
        category=k;
        pris=p;
    }

    public String toString() {
        String l=new String();
        l=name;

        return l;
    }

    public String fakta() {

        String l = new String();
        l=name;
        l+=" - "+company;
        l+=" är tillverkad i "+location;
        l+=" och kostar "+pris+" kr ";

        return l;

    }

    public String company() {
        String l=new String();
        l=company;

        return l;
    }

    public String location() {
        String l=new String();
        l=location;

        return l;
    }

    public String pris() {
        String l=new String();
        l=Integer.toString(pris);

        return l;
    }
}

