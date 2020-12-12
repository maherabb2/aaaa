package com.hipermarket.fisiere;

import com.hipermarket.Casier;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class ScriereFisier {
    private File fisier;

    public ScriereFisier(File fisier) {
        this.fisier = fisier;
    }

    public void scrieCaracter(char c) {
        try {
            FileWriter scriere = new FileWriter(fisier);
            scriere.write(c);
            scriere.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void scrieString(Object s) {
        try{
            FileWriter scrie=new FileWriter(fisier);
            if (s instanceof ArrayList) {
                ArrayList<Object> obiecte = (ArrayList) s;
                for (Object o: obiecte) {
                    scrie.write(String.valueOf(o.toString()));
                }
            } else {
                String value = (String) s;
                scrie.write(String.valueOf(s));
            }

            scrie.close();
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
}
