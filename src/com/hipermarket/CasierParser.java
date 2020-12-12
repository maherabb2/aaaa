package com.hipermarket;

public class CasierParser {

    // data-> casier1;parola1
    public Casier parse(String data) {
        String []elemente = data.split(";");

        String username = elemente[0];
        String parola = elemente[1];

        Casier casier = new Casier(username, parola);
        return casier;
    }
}
