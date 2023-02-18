package com.example.gui_version;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {
    private ReadBrenda reaction;
    private String filePath = "";
    @FXML
    private Text filepathStatus;

    @FXML
    private Button inhibitionButton;

    @FXML
    private Label inhibitionsStatus;

    @FXML
    private Text pathLabel;

    @FXML
    private Button reactionButton;

    @FXML
    private Label reactionsStatus;

    @FXML
    void fileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        List<File> f = fc.showOpenMultipleDialog(null);
        for(File file : f)
        {
            System.out.println(file.getAbsolutePath());
        }
        filepathStatus.setFill(Color.GREEN);
        pathLabel.setText("Path : " + f.get(0).toString());
        filePath = f.get(0).toString();
        reactionButton.setDisable(false);
        inhibitionButton.setDisable(false);
    }

    @FXML
    void loadInhibitions(ActionEvent event) {
        if(Color.RED == inhibitionsStatus.getTextFill()){
            inhibitionsStatus.setTextFill(Color.GREEN);
            inhibitionsStatus.setText("YES");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Inhibitions already set");
            alert.showAndWait();
        }
    }

    @FXML
    void loadReactions(ActionEvent event) {
        if(Color.RED == reactionsStatus.getTextFill()){
            reaction = new ReadBrenda(filePath);
            reaction.read("reaction");
            reactionsStatus.setTextFill(Color.GREEN);
            reactionsStatus.setText("YES");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Reactions already set");
            alert.showAndWait();
        }
    }

}
