/**
 * JavaFX UI design for the opening main menu Tokimons page
 *
 * @author  Jovanjot Bapla
 */

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.tools.design;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        deleteToki delete= new deleteToki();
        addToki addView= new addToki();
        allToki allView= new allToki();
        design style= new design();

        GridPane gridPane= new GridPane();
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setStyle("-fx-background-color: red; ");

        Button add= new Button("Add Tokimon");
        style.buttonHover(add);
        add.setPrefWidth(175);
        add.setPrefHeight(50);

        Button del= new Button("Delete Tokimon");
        style.buttonHover(del);
        del.setPrefWidth(175);
        del.setPrefHeight(50);

        Button all= new Button("See All Tokimon");
        style.buttonHover(all);
        all.setPrefWidth(175);
        all.setPrefHeight(50);

        gridPane.add(all,0,0);
        gridPane.add(del,0,1);
        gridPane.add(add,0,2);

        del.setOnAction(new EventHandler<ActionEvent>() {
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

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addView.addScreen();
                ((Node)e.getSource()).getScene().getWindow().hide();

            }
        });

        all.setOnAction(new EventHandler<ActionEvent>() {
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

        Scene scene = new Scene(gridPane, 210, 150);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tokidex");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
