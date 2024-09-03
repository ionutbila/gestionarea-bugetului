package com.example.gestionareabugetului;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class CalculateFeature {
    static Stage stage;

    public static int sizeOfFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file/output.txt"));
        List<String> lines = new ArrayList<>();
        String x = "";
        while (scanner.hasNext()) {
            x = scanner.nextLine();
            lines.add(x);
        }
        return lines.size();
    }

    public static int getTheTotal() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file/output.txt"));
        List<String> lines = new ArrayList<>();
        List<Integer> castiguri = new ArrayList<>();
        List<Integer> cheltuieli = new ArrayList<>();
        int budget = 0;

        String x = "";
        while (scanner.hasNext()) {
            x = scanner.nextLine();
            lines.add(x);
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split("\\|");


            if (tokens[0].trim().equalsIgnoreCase("salariu")) {
                castiguri.add(Integer.parseInt(tokens[2]));
            }
            if (tokens[0].trim().equalsIgnoreCase("cheltuieli")) {
                cheltuieli.add(Integer.parseInt(tokens[2]));
            }
        }

        for (int i = 0; i < castiguri.size(); i++) {
            budget += castiguri.get(i);
        }
        for (int i = 0; i < cheltuieli.size(); i++) {
            budget -= cheltuieli.get(i);

        }
        return budget;

    }


    public static int cheltuieliTotale() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file/output.txt"));
        List<String> lines = new ArrayList<>();
        List<Integer> cheltuieli = new ArrayList<>();
        int cheltuieliTotal = 0;

        String x = "";
        while (scanner.hasNext()) {
            x = scanner.nextLine();
            lines.add(x);
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split("\\|");

            if (tokens[0].trim().equalsIgnoreCase("cheltuieli")) {
                cheltuieli.add(Integer.parseInt(tokens[2]));
            }
        }

        for (int i = 0; i < cheltuieli.size(); i++) {
            cheltuieliTotal += cheltuieli.get(i);
        }

        return cheltuieliTotal;

    }



    public static List<Integer> getcastiguri(String month) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file/output.txt"));
        List<String> lines = new ArrayList<>();
        List<Integer> castiguri = new ArrayList<>();
        int budget = 0;

        String x = "";
        while (scanner.hasNext()) {
            x = scanner.nextLine();
            lines.add(x);
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split("\\|");

            if (tokens[1].equals(month)) {
                if (tokens[0].trim().equalsIgnoreCase("salariu")) {
                    castiguri.add(Integer.parseInt(tokens[2]));
                }
            }
        }


        return castiguri;

    }

    public static List<Integer> getcheltuieli(String month) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file/output.txt"));
        List<String> lines = new ArrayList<>();
        List<Integer> cheltuieli = new ArrayList<>();
        int budget = 0;

        String x = "";
        while (scanner.hasNext()) {
            x = scanner.nextLine();
            lines.add(x);
        }
        for (int i = 0; i < lines.size(); i++) {
            String[] tokens = lines.get(i).split("\\|");
            if (tokens[1].equals(month)) {
                if (tokens[0].trim().equalsIgnoreCase("cheltuieli")) {
                    cheltuieli.add(Integer.parseInt(tokens[2]));
                }
            }
        }

        return cheltuieli;

    }



    public static void calculateButtonPressed() throws FileNotFoundException {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("The result");


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);

        System.out.println(getTheTotal());

    }
}