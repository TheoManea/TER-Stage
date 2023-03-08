package com.example.gui_version;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {
    private ReadBrenda reactionDb, inhibitionDb;
    private String filePath = "";
    @FXML
    private TextField endReactionEntry;
    @FXML
    private Text filepathStatus;

    @FXML
    private Button inhibitionButton;

    @FXML
    private Label inhibitionsStatus;

    @FXML
    private TextArea logsEntry;

    @FXML
    private Text pathLabel;

    @FXML
    private Button reactionButton;

    @FXML
    private Label reactionsStatus;

    @FXML
    private TextField startReactionEntry;

    @FXML
    void fileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        List<File> f = fc.showOpenMultipleDialog(null);
        for(File file : f)
        {
            System.out.println(file.getAbsolutePath());
        }
        filepathStatus.setFill(Color.GREEN);
        filepathStatus.setText("YES");
        pathLabel.setText("Path : " + f.get(0).toString());
        filePath = f.get(0).toString();
        reactionButton.setDisable(false);
        inhibitionButton.setDisable(false);
    }

    @FXML
    void loadInhibitions(ActionEvent event) {
        if(Color.RED == inhibitionsStatus.getTextFill()){
            inhibitionDb = new ReadBrenda(filePath);
            inhibitionDb.read("inhibition");
            inhibitionsStatus.setTextFill(Color.GREEN);
            inhibitionsStatus.setText("YES");

            printLog(Color.BLACK,"\n>>>>> " + inhibitionDb.getNbinhibition() + "  Inhibitions have been loaded");
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
            reactionDb = new ReadBrenda(filePath);
            reactionDb.read("reaction");
            reactionsStatus.setTextFill(Color.GREEN);
            reactionsStatus.setText("YES");
            printLog(Color.BLACK,"\n>>>>> " + reactionDb.getNbreaction() + " Reactions have been loaded");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Reactions already set");
            alert.showAndWait();
        }
    }

    @FXML
    void searchReactionPath(ActionEvent event) {
        String startingPoint = startReactionEntry.getText();
        String endingPoint = endReactionEntry.getText();

        if(startingPoint.isEmpty() || endingPoint.isEmpty()){
            popup("Please fill both entries");
        }
        else{
            SearchReaction searchEngine = new SearchReaction(reactionDb.getReactionList(),inhibitionDb.getInhibitionList());
            searchEngine.settingReactionAttributes();
            printLog(Color.BLACK,"\n>>>>> Attributes for reactions have been setted ");
            searchEngine.setStartingPoint(startingPoint);
            searchEngine.setEndingPoint(endingPoint);
            printLog(Color.BLACK,"\n>>>>> Looking for a path of type : \"" + startingPoint + "\" -> ... -> \"" + endingPoint + "\"");

            if(!searchEngine.existStartingPoint())
                printLog(Color.BLACK,"\n>>>>> The initial chems is not in the database ...");

            if(!searchEngine.existEndingPoint())
                printLog(Color.BLACK,"\n>>>>> The final product is not present in the database...");

            if(searchEngine.existStartingPoint() && searchEngine.existEndingPoint())
                printLog(Color.BLACK,"\n>>>>> The path is theoretically possible !");



        }


    }


    void printLog(Color color, String text){
        logsEntry.appendText(text);
    }

    void popup(String str){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(str);
        alert.showAndWait();
    }
}
