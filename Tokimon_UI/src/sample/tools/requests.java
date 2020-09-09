/**
 * requests class controls the request sent from the client side to the server side and also implements how to
 * layout the results on the UI
 *
 * @author  Jovanjot Bapla
 */

package sample.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class requests {

    ArrayList<toki> tokis= new ArrayList<>();

    public void getTokis(){
        try {
            URL url = new URL("http://localhost:8080/GET/api/tokimon/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            toki[] tokiList = new Gson().fromJson(br, toki[].class);
            for (toki tokiObj : tokiList) {
                tokis.add(tokiObj);
            }


            System.out.println(connection.getResponseCode());
            connection.disconnect();
        }
        catch (IOException e){

        }

    }

    public ComboBox<toki> comboGet(Label notify, Circle tokiShape) throws IOException{
        ComboBox<toki> box= new ComboBox<>();

            getTokis();
            box.getItems().addAll(tokis);
            box.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    notify.setText("Click Tokimon\n   for STATS");
                    notify.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: yellow");

                    if(box.getValue().getColour().equals("BLUE")){
                        tokiShape.setFill(Color.BLUE);
                    }
                    if(box.getValue().getColour().equals("RED")){
                        tokiShape.setFill(Color.RED);
                    }
                    if(box.getValue().getColour().equals("GREEN")){
                        tokiShape.setFill(Color.GREEN);
                    }
                    if(box.getValue().getColour().equals("YELLOW")){
                        tokiShape.setFill(Color.YELLOW);
                    }
                    if(box.getValue().getColour().equals("ORANGE")){
                        tokiShape.setFill(Color.ORANGE);
                    }
                    if(box.getValue().getColour().equals("PURPLE")){
                        tokiShape.setFill(Color.PURPLE);
                    }
                    if(box.getValue().getColour().equals("PINK")){
                        tokiShape.setFill(Color.PINK);
                    }

                    tokiShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Stage substage= new Stage();
                            GridPane gridPane= new GridPane();
                            gridPane.setVgap(10);
                            gridPane.setHgap(10);
                            gridPane.setStyle("-fx-background-color: red");


                            Label name= new Label("NAME");
                            Label type= new Label("ABILITY");
                            Label strength= new Label("STRENGTH");
                            Label weight= new Label("WEIGHT");
                            Label height = new Label("HEIGHT");
                            Label color= new Label("COLOR");

                            Label name1= new Label(box.getValue().getName());
                            Label type1= new Label(box.getValue().getAbility());
                            Label strength1= new Label(String.format("%.1f",box.getValue().getStrength()));
                            Label weight1= new Label(String.format("%.1f",box.getValue().getWeight()));
                            Label height1 = new Label(String.format("%.1f",box.getValue().getHeight()));
                            Label color1= new Label(box.getValue().getColour());

                            gridPane.add(name,0,0);
                            gridPane.add(name1,0,1);
                            gridPane.add(type,0,2);
                            gridPane.add(type1,0,3);
                            gridPane.add(strength,0,4);
                            gridPane.add(strength1,0,5);
                            gridPane.add(weight,1,0);
                            gridPane.add(weight1,1,1);
                            gridPane.add(height,1,2);
                            gridPane.add(height1,1,3);
                            gridPane.add(color,1,4);
                            gridPane.add(color1,1,5);


                            for (Node label : gridPane.getChildren()){
                                GridPane.setHalignment(label, HPos.CENTER);
                                label.setStyle("-fx-text-fill: white; -fx-font-weight: bold");
                            }

                            Scene subscene = new Scene(gridPane,125,175);

                            substage.setScene(subscene);
                            substage.setTitle("Tokidex: STATS");
                            substage.show();
                        }
                    });


                }
            });


        return box;
    }

    public ListView<toki> comboDel(Button submit, Label message)throws IOException{

        getTokis();
        ListView<toki> box= new ListView<>();
        box.getItems().addAll(tokis);
        box.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                message.setText("Press delete\nto remove\n"+box.getSelectionModel().getSelectedItem().getName());
            }
        });



        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    URL url = new URL("http://localhost:8080/DELETE/api/tokimon/" + box.getSelectionModel().getSelectedItem().getId());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    connection.connect();
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();

                    message.setText("REMOVED\n"+box.getSelectionModel().getSelectedItem().getName());
                    box.getItems().remove(box.getSelectionModel().getSelectedIndex());


                }
                catch (IOException e){

                }
            }
        });
        return box;
    }

    public void post(Button submit, TextField name, TextField type, Slider weight, Slider height, Slider strength, ComboBox color, Label missing){

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(!(name.getText().equals("")) && !(type.getText().equals("")) && !(color.getValue()==null)) {
                    try {
                        missing.setText("TOKIMON ADDED");
                        URL url = new URL("http://localhost:8080/POST/api/tokimon/add");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true); //auto set as false
                        connection.setRequestProperty("Content-Type", "application/json");
                        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                        wr.write("{" +
                                "\"id\": 999," +
                                "\"name\": \"" + name.getText() + "\"," +
                                "\"weight\": " + weight.getValue() + "," +
                                "\"height\": " + height.getValue() + "," +
                                "\"ability\": \"" + type.getText() + "\"," +
                                "\"strength\": " + strength.getValue() + "," +
                                "\"colour\": \"" + color.getValue() + "\"" +
                                "}");
                        wr.flush();
                        wr.close();
                        connection.connect();

                        System.out.println(connection.getResponseCode());
                        connection.disconnect();
                        name.setText("");
                        weight.setValue(150.0);
                        height.setValue(150.0);
                        type.setText("");
                        strength.setValue(25.0);
                        color.setValue(null);

                        name.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                missing.setText("");
                            }
                        });

                    } catch (IOException e) {

                    }
                }
                else{
                    missing.setText("MISSING INFO!");
                }

            }
        });


    }



}
