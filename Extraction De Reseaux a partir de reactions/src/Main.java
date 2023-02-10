import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // get path of my db
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString()+"/src/data/brenda";

        //reading my File
        ReadBrenda db = new ReadBrenda(s);
        ReadBrenda db2 = new ReadBrenda(s);
        db.read("reaction");
        db2.read("inhibition");
        System.out.println("Il y a " + db.getNbreaction() + " réactions au total");
        System.out.println("Il y a " + db2.getNbinhibition() + " inhibitions au total");
    }
}