package com.example.gestionareabugetului;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HelloApplication extends Application {
    Stage stage;
    Scene scene;
    BorderPane layout;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage = new Stage();
        stage.setTitle("Administratie buget");

        Menu menu1 = new Menu("_File");
        Menu menu2 = new Menu("_Edit");
        Menu menu3 = new Menu("_Help");

        menu1.getItems().add(new MenuItem("Open"));
        menu1.getItems().add(new MenuItem("New"));
        menu1.getItems().add(new MenuItem("Save"));
        menu1.getItems().add(new MenuItem("Close"));



        menu2.getItems().add((new MenuItem("Add")));
        menu2.getItems().add((new MenuItem("Delete")));

        menu3.getItems().add(new MenuItem("Help"));

        Label welcomeLabel = new Label("Welcome to our hotel's administration application!");
        Font font = Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16);
        welcomeLabel.setFont(font);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(8, 8, 8, 8));
        gridPane.setVgap(8);
        gridPane.setHgap(8);

        Label salariuLabel = new Label("How much did you earn?");
        Label salariuMonth = new Label("When? Which month");
        Label cheltuieliLabel = new Label("How much did you spend?");
        Label cheltuieliMonth = new Label("When? Which month");

        TextField salariuField = new TextField();
        salariuField.setPromptText("salariu");
        salariuField.setMinWidth(100);
        TextField monthSalariu = new TextField();
        monthSalariu.setPromptText("month");
        monthSalariu.setMinWidth(100);

        TextField cheltuieliField = new TextField();
        cheltuieliField.setPromptText("cheltuieli");
        cheltuieliField.setMinWidth(100);
        TextField monthCheltuieli = new TextField();
        monthCheltuieli.setPromptText("month");
        monthCheltuieli.setMinWidth(100);

        Button earnButton = new Button("Earn");
        earnButton.setMinWidth(120);
        earnButton.setOnAction(e -> {
            if (!(monthSalariu.getText().isEmpty() || salariuField.getText().isEmpty())) {

                try {
                    FileWriter writer = new FileWriter("file/output.txt", true);
                    writer.write("salariu " + "|" + monthSalariu.getText() + "|" + salariuField.getText() + "|" + "\n");
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        Button spendButton = new Button("Spend");
        spendButton.setMinWidth(120);
        spendButton.setOnAction(e -> {
            if (!(monthCheltuieli.getText().isEmpty() || cheltuieliField.getText().isEmpty())) {
                try {
                    FileWriter writer = new FileWriter("file/output.txt", true);
                    writer.write("cheltuieli " + "|" + monthCheltuieli.getText() + "|" + cheltuieliField.getText() + "|" + "\n");
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        Button calculateButton = new Button("Show your budget");
        calculateButton.setMinWidth(120);
        GridPane.setConstraints(calculateButton, 4, 2);

        calculateButton.setOnAction(e -> {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Hello! Here is your budget!");
            try {
                alert.setContentText("The budget is "+CalculateFeature.getTheTotal()+"\n"+ "The expenses are: "+CalculateFeature.cheltuieliTotale());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            alert.showAndWait();
            try {
                CalculateFeature.calculateButtonPressed();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });


        GridPane.setConstraints(salariuLabel, 0, 0);
        GridPane.setConstraints(salariuField, 1, 0);
        GridPane.setConstraints(salariuMonth, 2, 0);
        GridPane.setConstraints(monthSalariu, 3, 0);
        GridPane.setConstraints(earnButton, 4, 0);

        GridPane.setConstraints(cheltuieliLabel, 0, 1);
        GridPane.setConstraints(cheltuieliField, 1, 1);
        GridPane.setConstraints(cheltuieliMonth, 2, 1);
        GridPane.setConstraints(monthCheltuieli, 3, 1);
        GridPane.setConstraints(spendButton, 4, 1);


        gridPane.getChildren().addAll(salariuLabel, salariuField, cheltuieliLabel, cheltuieliField,
                earnButton, salariuMonth, cheltuieliMonth, monthSalariu, monthCheltuieli, spendButton,
                calculateButton);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);

        layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(gridPane);

        scene = new Scene(layout, 800, 150);
        stage.setScene(scene);
        stage.show();
    }

    public String getChoice(ChoiceBox<String> choiceBox){
        return choiceBox.getValue();
    }
}