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
        String 1=new String();
        1=name;
        1+=" - "+company;
        1+=" är tillverkad i "+location;
        1+=" och kostar "+pris+" kr ";

        return 1;

    }

    public String company() {
        String 1=new String();
        1=company;

        return 1;
    }

    public String location() {
        String 1=new String();
        1=location;

        return 1;
    }

    public String pris() {
        String l=new String();
        l=Integer.toString(pris);

        return l;
    }
}

