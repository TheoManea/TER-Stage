import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // get path of my db
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString()+"/src/data/testFile.txt";

        ArrayList<Reaction> l = new ArrayList<>();
        //reading my File
        ReadBrenda db = new ReadBrenda(s);
        db.read("reaction");

        SearchReaction sr = new SearchReaction(db.getReactionList(),db.getInhibitionList());
        sr.settingReactionAttributes();
        sr.setStartingPoint("Ethanol");
        sr.setEndingPoint("NADH");

        System.out.println("Searching for : " + sr.startingPoint + " -> ... -> " + sr.endingPoint);
        if(sr.existStartingPoint() && sr.existEndingPoint())
            System.out.println("The path is theoratically possible");
        else{
            System.out.println("The path is not possible");
            System.exit(0);
        }

        System.out.println("Building our rootChemical list ...");
        sr.buildRootChemical();
        /*System.out.println("Our rootChemical are : ");
        for(String str : sr.getRootChemical()){
            System.out.println(str);
        }*/
        System.out.println("Building our rootToNext hashMap ...");
        sr.buildRootToNextHashMap();
        /*System.out.println("Out nextToRoot hashMap : ");
        for (Map.Entry<String, ArrayList<String>> entry : sr.getRootToNext().entrySet()) {
            System.out.println(entry.getKey() + " -> " + Arrays.toString(entry.getValue().toArray(new String[0])));
        }*/


    }
}