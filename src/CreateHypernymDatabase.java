import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * class that creates a hypernym database scans all the files in the given path in the argument,
 * after mapping all hypernym-hyponym relation export it all into a output file that was given as an argument.
 * @author ozamoyal
 */
public class CreateHypernymDatabase {
/**
 * get a corpus path and a output file path, search all files for hypernyms and their hyponyms and put them in the
 * database, finally export all the database into a output file that is given also as argument.
 * @param args a path to a corpus and an output file.
 */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("No Valid Arguments, Please Enter A Corpus Path And A Output File Path");
            return;
        }

        String corpus = args[0];
        String output = args[1];
        HypernymDatabase db = new HypernymDatabase();
        BufferedReader is = null;
        File dir = new File(corpus);
        File[] files = dir.listFiles();
        File outputFile = new File(output);
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
        db.filterHypernyms();

        try {
            OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outputFile)); // wrapper that can write
                                                                                              // strings
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