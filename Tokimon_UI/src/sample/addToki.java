/**
 * JavaFX UI design for the add Tokimons page
 *
 * @author  Jovanjot Bapla
 */

package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.tools.design;
import sample.tools.requests;

import java.io.IOException;


public class addToki {

    public void addScreen(){
        design style= new design();
        requests obj=new requests();
        deleteToki delete= new deleteToki();
        allToki allView= new allToki();

        Stage subStage= new Stage();

        Label nameLabel = new Label("Name: ");
        nameLabel.setStyle("-fx-background-color: white");
        GridPane.setHalignment(nameLabel, HPos.CENTER);
        nameLabel.setPrefWidth(100);
        nameLabel.setPrefHeight(20);
        nameLabel.setPadding(new Insets(0, 0, 0, 30));

        Label weightLabel = new Label("Weight (kg): ");
        weightLabel.setStyle("-fx-background-color: white");
        GridPane.setHalignment(weightLabel,HPos.CENTER);
        weightLabel.setPadding(new Insets(0, 0, 0, 30));
        weightLabel.setPrefWidth(100);
        weightLabel.setPrefHeight(20);

        Label heightLabel = new Label("Height (cm): ");
        heightLabel.setStyle("-fx-background-color: white");
        GridPane.setHalignment(heightLabel,HPos.CENTER);
        heightLabel.setPadding(new Insets(0, 0, 0, 30));
        heightLabel.setPrefWidth(100);
        heightLabel.setPrefHeight(20);

        Label abilityLabel = new Label("Ability: ");
        abilityLabel.setStyle("-fx-background-color: white");
        GridPane.setHalignment(abilityLabel,HPos.CENTER);
        abilityLabel.setPadding(new Insets(0, 0, 0, 30));
        abilityLabel.setPrefWidth(100);
        abilityLabel.setPrefHeight(20);

        Label strengthLabel = new Label("Strength: ");
        strengthLabel.setStyle("-fx-background-color: white");
        GridPane.setHalignment(strengthLabel,HPos.CENTER);
        strengthLabel.setPadding(new Insets(0, 0, 0, 30));
        strengthLabel.setPrefWidth(100);
        strengthLabel.setPrefHeight(20);

        Label colorLabel = new Label("Color: ");
        colorLabel.setStyle("-fx-background-color: white");
        GridPane.setHalignment(colorLabel,HPos.CENTER);
        colorLabel.setPadding(new Insets(0, 0, 0, 30));
        colorLabel.setPrefWidth(100);
        colorLabel.setPrefHeight(20);

        Label missing= new Label();
        missing.setStyle("-fx-text-fill: yellow; -fx-font-weight: bold");



        Slider weightField = new Slider(0,500,150);
        Label weightValue= new Label("150.0");
        weightValue.setStyle("-fx-text-fill: white");
        weightValue.setPadding(new Insets(0,0,0,15));
        weightField.setShowTickLabels(true);
        weightField.setMajorTickUnit(100);
        weightField.setShowTickMarks(true);
        weightField.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                weightValue.setText(String.format("%.1f",t1));
            }
        });

        Slider heightField = new Slider(0,500,150);
        Label heightValue= new Label("150.0");
        heightValue.setStyle("-fx-text-fill: white");
        heightValue.setPadding(new Insets(0,0,0,15));
        heightField.setShowTickLabels(true);
        heightField.setShowTickMarks(true);
        heightField.setMajorTickUnit(100);
        heightField.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                heightValue.setText(String.format("%.1f",t1));
            }
        });

        Slider strengthField = new Slider(0,100,25);
        Label strengthValue= new Label("25.0");
        strengthValue.setStyle("-fx-text-fill: white");
        strengthValue.setPadding(new Insets(0,0,0,15));
        strengthField.setShowTickLabels(true);
        strengthField.setShowTickMarks(true);
        strengthField.setMinorTickCount(5);
        strengthField.setMajorTickUnit(20);
        strengthField.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                strengthValue.setText(String.format("%.1f",t1));
            }
        });

        ComboBox<String> colorField = new ComboBox<>();
        colorField.setStyle("-fx-border-color: black; -fx-background-color: white");
        colorField.getItems().addAll("BLUE","RED","YELLOW","ORANGE","GREEN","PINK","PURPLE");

        TextField nameField = new TextField();
        TextField abilityField = new TextField();



        //BUTTONS
        Button submit = new Button("Submit");
        style.buttonHover(submit);


        obj.post(submit, nameField, abilityField, weightField, heightField, strengthField, colorField, missing);



        Button deleteToki= new Button("Delete Tokimon");
        deleteToki.setPrefWidth(125);
        style.buttonHover(deleteToki);
        deleteToki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    delete.deleteScreen();
                    ((Node) e.getSource()).getScene().getWindow().hide();
                }
                catch (IOException f){

                }
            }
        });

        Button allToki= new Button("All Tokimon");
        allToki.setPrefWidth(125);
        style.buttonHover(allToki);
        allToki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    allView.allScreen();
                    ((Node) e.getSource()).getScene().getWindow().hide();
                }
                catch (IOException ex){

                }
            }
        });


        //SET GRID
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(10,10,10,10));
        gridpane.setStyle("-fx-background-color: red");
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        gridpane.add(deleteToki,0,0);
        gridpane.add(allToki, 1, 0);
        gridpane.add(submit, 1, 7);
        gridpane.add(missing,1,8);

        gridpane.add(nameLabel, 0, 1);
        gridpane.add(nameField, 1, 1);
        gridpane.add(weightLabel, 0, 2);
        gridpane.add(weightField, 1, 2);
        gridpane.add(weightValue,2,2);
        gridpane.add(heightLabel,0,3);
        gridpane.add(heightField,1,3);
        gridpane.add(heightValue, 2,3);
        gridpane.add(abilityLabel,0,4);
        gridpane.add(abilityField,1,4);
        gridpane.add(strengthLabel,0,5);
        gridpane.add(strengthField,1,5);
        gridpane.add(strengthValue,2,5);
        gridpane.add(colorLabel,0,6);
        gridpane.add(colorField,1,6);


        Scene scene = new Scene(gridpane, 365, 365);

        subStage.setTitle("tokidex: Add Tokimon");
        subStage.setScene(scene);
        subStage.show();

    }

}
