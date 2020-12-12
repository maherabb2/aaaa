package com.hipermarket.fisiere;

import java.io.File;
import java.util.Scanner;

public class CitireFisier {
    private File fisier;

    public CitireFisier(File fisier) {
        this.fisier = fisier;
    }

    public char citireCaracter() {
        char rezultat;

        try {
            Scanner citire = new Scanner(fisier);
            String line = citire.nextLine();
            rezultat = line.charAt(0);
            citire.close();
        } catch (Exception e) {
            rezultat = 'x';
        }

        return rezultat;
    }

    public String citireString() {
        String rezultat = "";

        try {
            Scanner citire = new Scanner(fisier);
            while (citire.hasNextLine()) {
                String line = citire.nextLine();
                if (! line.trim().isEmpty()) {
                    rezultat = rezultat + line + "\n";
                }
            }
            citire.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rezultat;
    }
}
