/**
 * JavaFX UI design for the all Tokimons page
 *
 * @author  Jovanjot Bapla
 */

package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.tools.design;
import sample.tools.requests;
import sample.tools.toki;

import java.io.IOException;


public class allToki {



    public void allScreen() throws IOException {
        requests obj= new requests();
        addToki addView= new addToki();
        deleteToki deleteView= new deleteToki();
        design style= new design();

        Stage substage= new Stage();

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: red;");
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        StackPane stack= new StackPane();
        stack.setPrefHeight(125);
        stack.setStyle("-fx-background-color: white");

        Label notify= new Label();
        Label instruc= new Label("Select a Tokimon");
        instruc.setStyle("-fx-text-fill: white; -fx-font-weight: bold");

        Button deleteToki= new Button("Delete Tokimon");
        deleteToki.setPrefWidth(125);
        style.buttonHover(deleteToki);
        deleteToki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    deleteView.deleteScreen();
                    ((Node) e.getSource()).getScene().getWindow().hide();
                }
                catch (IOException f){
                    System.out.println();
                }
            }
        });

        Button addToki= new Button("Add Tokimon");
        addToki.setPrefWidth(125);
        style.buttonHover(addToki);
        addToki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addView.addScreen();
                ((Node)e.getSource()).getScene().getWindow().hide();
            }
        });

        Circle tokiShape= new Circle(50,50,50);

        ComboBox<toki> allTokis;


        gridPane.add(deleteToki,0,0);
        gridPane.add(addToki,0,1);
        gridPane.add(instruc,1,0);
        GridPane.setHalignment(instruc,HPos.CENTER);
        gridPane.add(notify,0,2);
        GridPane.setHalignment(notify, HPos.CENTER);
        gridPane.add(stack,1,2);
        stack.getChildren().add(tokiShape);
        stack.setPadding(new Insets(3,2,2,2));
        GridPane.setHalignment(tokiShape, HPos.CENTER);

        allTokis=obj.comboGet(notify, tokiShape);
        allTokis.setStyle("-fx-border-color: black; -fx-background-color: #ffffff");
        allTokis.setPrefWidth(125);

        gridPane.add(allTokis,1,1);


        Scene subscene = new Scene(gridPane,300,200);

        substage.setScene(subscene);
        substage.setTitle("Tokidex: All Tokimon");
        substage.show();
    }




}
