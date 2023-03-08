import java.util.ArrayList;
import java.util.HashMap;

public class SearchReaction {
    public String startingPoint, endingPoint;
    private ArrayList<Reaction> reactionList;
    private ArrayList<Inhibition> inhibitionList;
    private ArrayList<String> rootChemical = new ArrayList<>();
    private HashMap<String,ArrayList<String>> rootToNext = new HashMap<>();
    public SearchReaction(ArrayList<Reaction> rList, ArrayList<Inhibition> iList){
        reactionList = rList;
        inhibitionList = iList;
    }
    public ArrayList<String> getRootChemical() {
        return rootChemical;
    }
    public HashMap<String, ArrayList<String>> getRootToNext() {
        return rootToNext;
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

    public void buildRootChemical(){
        if(reactionList.size() == 0){
            System.out.println("The reaction's list is empty ...");
        }
        else{
            for(Reaction r : reactionList){
                for(String s : r.getSubstrats()){
                    if(!rootChemical.contains(s)){
                        rootChemical.add(s);
                    }
                }
            }
        }
    }

    public void buildRootToNextHashMap(){
        if(rootChemical.size() == 0){
            System.out.println("The root list is empty ...");
        }
        else{
            for(String s : rootChemical){
                ArrayList<String> tmp = new ArrayList<>();
                for(Reaction r : reactionList){
                    for(String s1 : r.getSubstrats()){
                        if(s.equals(s1)){
                            for(String p : r.getProduits()){
                                if(!tmp.contains(p))
                                    tmp.add(p);
                            }
                        }
                    }
                }
                rootToNext.put(s,tmp);
            }
        }
    }
}