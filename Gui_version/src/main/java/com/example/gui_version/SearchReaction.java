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
    public void settingReactionAttributes(){
        for(Reaction r : reactionList){
            r.setAttributes();
        }
    }
    public boolean existStartingPoint(){
        for(Reaction r : reactionList){
            for(String sub : r.getSubstrats()){
                if(sub.equals(startingPoint))
                    return true;
            }
        }
        return false;
    }

    public boolean existEndingPoint(){
        for(Reaction r : reactionList){
            for(String sub : r.getProduits()){
                if(sub.equals(endingPoint))
                    return true;
            }
        }
        return false;
    }
}
