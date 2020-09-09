/**
 * design holds the style guide for all the buttons
 *
 * @author  Jovanjot Bapla
 */

package sample.tools;

import javafx.scene.control.Button;


public class design {
    public void buttonHover(Button button){
        button.setStyle("-fx-background-color: #ffffff; ");
        button.setOnMouseEntered(e -> button.setStyle("-fx-border-color: black; -fx-background-color: #ffffff; -fx-font-weight: bold" ));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #ffffff; " ));
    }
}
