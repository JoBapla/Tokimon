/**
 * JavaFX UI design for the delete Tokimons page
 *
 * @author  Jovanjot Bapla
 */

package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.tools.design;
import sample.tools.requests;
import sample.tools.toki;

import java.io.IOException;

public class deleteToki {

    public void deleteScreen()throws IOException{
        requests box= new requests();
        ComboBox<toki> list= new ComboBox<>();
        design style= new design();

        addToki addView= new addToki();
        allToki allView= new allToki();

        Stage substage= new Stage();

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setStyle("-fx-background-color: red");

        Button addToki= new Button("Add Tokimon");
        style.buttonHover(addToki);
        addToki.setPrefWidth(125);
        addToki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                addView.addScreen();
                ((Node)e.getSource()).getScene().getWindow().hide();

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
                    System.out.println();
                }
            }
        });
        Button delete= new Button("Delete");
        delete.setPrefWidth(125);
        style.buttonHover(delete);

        Label message= new Label();
        message.setStyle("-fx-font-weight: bold; -fx-text-fill: yellow");

        gridPane.add(delete,0,2);
        gridPane.add(allToki,0,0);
        gridPane.add(addToki,1,0);
        gridPane.add(message,1,1);

        ListView<toki> dropDown;
        dropDown=box.comboDel(delete, message);

        dropDown.setPrefHeight(100);
        dropDown.setPrefWidth(125);

        gridPane.add(dropDown,0,1);

        Scene subscene = new Scene(gridPane,250,175);

        substage.setScene(subscene);
        substage.setTitle("Tokidex: Delete Tokimon");
        substage.show();

    }

}
