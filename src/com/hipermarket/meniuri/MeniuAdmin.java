package com.hipermarket.meniuri;

import com.hipermarket.Casier;
import com.hipermarket.CasierParser;
import com.hipermarket.fisiere.CitireFisier;
import com.hipermarket.fisiere.ScriereFisier;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MeniuAdmin extends MeniuAngajat {
    @Override
    public Meniu interpreteazaComanda(char c) {
        Meniu meniu = this;

        switch (c) {
            case '1':
                adauga();
                break;
            case '3':
                listare();
                break;
            case '2':
                sterge();
                break;
        }

        return this;
    }

    @Override
    public void afisare() {
        System.out.println("com.hipermarket.meniuri.Meniu Admin");
    }


    @Override
    public void comutareClient() {

    }

    @Override
    public void adauga() {
        System.out.println("Am intrat in adauga casier");
        Casier casier = null;

        File messages = new File("database/messages.txt");
        try {
            Scanner scanner = new Scanner(messages);
            String line = scanner.nextLine();
            CasierParser casierParser = new CasierParser();
            casier = casierParser.parse(line);

            System.out.println("Casierul din message: " + casier.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayList<Casier> casieri = new ArrayList<>();
        File casieriFile = new File("database/casieri.txt");
        try {
            Scanner scanner = new Scanner(casieriFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                CasierParser casierParser = new CasierParser();
                Casier c = casierParser.parse(line);
                casieri.add(c);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        boolean rezutat = false;
        for (Casier c: casieri) {
            System.out.println("Casier: " + c.toString());
            if (! c.equals(casier)) {
                System.out.println("Casierul nu exista");
                rezutat = true;

                try {
                    FileWriter writer = new FileWriter(casieriFile, true);
                    writer.write(casier.toString());
                    writer.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Rezultat este: " + rezutat);

        try {
            File output = new File("database/output.txt");
            FileWriter writer = new FileWriter(output);
            writer.write(String.valueOf(rezutat));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listare() {
        File file = new File("database/casieri.txt");
        ArrayList<Casier> casiers = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                CasierParser casierParser = new CasierParser();
                Casier casier = casierParser.parse(line);
                casiers.add(casier);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("database/output.txt");
            for (Casier c : casiers) {
                writer.write(c.toString());

            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void sterge() {
        ArrayList<Casier> casiers = new ArrayList<>();

        File file = new File("database/casieri.txt");
        CitireFisier citireFisier = new CitireFisier(file);
        String[] infoCasieri = citireFisier.citireString().split("\n");
        for (String info : infoCasieri) {
            CasierParser casierParser = new CasierParser();
            Casier casier = casierParser.parse(info);
            casiers.add(casier);
        }

        File fisier=new File("database/messages.txt");
        citireFisier = new CitireFisier(fisier);
        String user = citireFisier.citireString();

        boolean rezultat=false;
        for(Iterator it=casiers.iterator();it.hasNext();){
            Casier c=(Casier)it.next();
            if(c.getUser().equals(user)){
                it.remove();
                rezultat=true;
                break;
            }
        }

        File output=new File("database/output.txt");
        ScriereFisier scriereFisier = new ScriereFisier(output);
        scriereFisier.scrieString(String.valueOf(rezultat));

        File casieri=new File("database/casieri.txt");
        scriereFisier = new ScriereFisier(casieri);
        scriereFisier.scrieString(casiers);
    }

    private void verificareCasier() {

    }
}