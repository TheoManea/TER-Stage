import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadBrenda {
    /*
    *   Réactions à partir de la ligne 0
    *   Inhibitions à partir de la ligne 3916
     */
    private String filepath = "";
    private ArrayList<Reaction> reactionList = new ArrayList<Reaction>();
    public ReadBrenda(String path){
            filepath = path;
    }

    public void read(String mode){
        try {
            // reading Objects
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);

            // boucle de lecture
            boolean reactionTurn = false;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                // on traite le cas des lignes non-vides
                if(data.length() > 0) {
                    // si la dites ligne nous indique la partie du fichier
                    if (data.charAt(0) == '/' && data.charAt(1) == '/'){
                        if(data.charAt(3) == 'R'){
                            reactionTurn = true;
                        }
                        else if(data.charAt(3) == 'I'){
                            reactionTurn = false;
                        }
                    }
                }

                // maintenant on lit en fonction du mode
                if(data.length() > 0 && data.charAt(0) != '/')
                switch (mode){
                    case "reaction":
                        if(reactionTurn) {
                            reactionList.add(new Reaction(data));
                            //System.out.println(data);
                        }
                        break;
                    case "inhibition":
                        if(!reactionTurn) {
                            //System.out.println(data);
                        }
                        break;
                    case "plain":
                        System.out.println(data);
                        break;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur de lecture.");
            e.printStackTrace();
        }
    }
    public void testReactions(boolean infoMode){
        if(getReactionList().size() == 0){
            getReactionList();
            testReactions(infoMode);
        }
        else{
            ArrayList<Integer> errorLine = new ArrayList<>();
            int nbReactions = 0;
            int nbLinesWellFormated = 0;
            for(Reaction r : reactionList){
                nbReactions++;
                boolean correct = r.correctlyFormatted();
                if(correct)
                    nbLinesWellFormated++;
                else if(!correct && infoMode )
                    errorLine.add(nbReactions);
            }
            System.out.println("Nombres de lignes correctes " + nbLinesWellFormated);
            System.out.println("Nombres de lignes incorrectes " + (nbReactions - nbLinesWellFormated));
            if(infoMode){
                StringBuilder sb = new StringBuilder();
                for (Integer number : errorLine) {
                    int shift = number+2;
                    sb.append(shift + " ");
                }
                System.out.println("Lignes erronés : "+ sb);
            }

        }
    }
    public ArrayList<Reaction> getReactionList(){
        return reactionList;
    }

}
