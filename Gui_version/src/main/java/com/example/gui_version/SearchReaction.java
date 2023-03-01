package com.example.gui_version;

import java.util.ArrayList;

public class SearchReaction {
    String startingPoint, endingPoint;
    private ArrayList<Reaction> reactionList;
    private ArrayList<Inhibition> inhibitionList;
    public SearchReaction(ArrayList<Reaction> rList, ArrayList<Inhibition> iList){
        reactionList = rList;
        inhibitionList = iList;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public void setEndingPoint(String endingPoint){
        this.endingPoint = endingPoint;
    }

    public boolean existStartingPoint(){
        for(int i = 0; i < 20; i++){
            System.out.println("Ligne n°" + i);
            reactionList.get(i).setAttributes();
        }

        return false;
    }
}
