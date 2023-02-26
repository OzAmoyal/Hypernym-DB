import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * class that searches all hypernyms for a given lemma.
 * @author ozamoyal
 */
public class DiscoverHypernym {
    /**
     * create a lemma hypernym database and search all the files in the given arguments with the given lemma.
     * @param args a path for a corpus and a lemma
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("No Valid Arguments, Please Enter A Corpus Path And A Lemma");
            return;
        }
        String lemmaString = args[1].toLowerCase();
        String corpus = args[0];
        File dir = new File(corpus);
        File[] files = dir.listFiles();
        NounPhrase lemma = NounPhrase.getNounPhrase(lemmaString);
        HypernymDatabase db = new LemmaHypernymDatabase(lemma);
        BufferedReader is = null;
        for (File file : files) {
            try {
                is = new BufferedReader(// wrapper that reads ahead
                        new InputStreamReader(// wrapper that reads characters
                                new FileInputStream(file)));
                db.readData(is);
            } catch (IOException e) {
                System.out.println(" Something went wrong while reading !");
            } finally {
                if (is != null) { // Exception might have happened at constructor
                    try {
                        is.close(); // closes FileInputStream too
                    } catch (IOException e) {
                        System.out.println(" Failed closing the file !");

                    }

                }
            }
        }
        try {
            OutputStreamWriter os = new OutputStreamWriter(System.out);
            db.export(os);
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            if (is != null) { // Exception might have happened at constructor
                try {
                    is.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");

                }

            }
        }
    }
}
