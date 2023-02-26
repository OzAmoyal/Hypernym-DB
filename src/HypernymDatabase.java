import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * a class for a hypernym database that keeps hypernyms that are found in the proccess.
 * @author ozamoyal
 */
public class HypernymDatabase {
    private Map<String, Hypernym> relationMap;
    private List<HearstPattern> patterns;
/**
 * a constructor for a hypernym database object.
 */
    public HypernymDatabase() {
        //initialize a tree map to keep all hypernyms.
        this.relationMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        //create the patterns to look for.
        this.patterns = createPatterns();

    }
/**
 * getter for a specific hypernym by its name, if it's in the map returns it and if not creates a new
 * instance and returns it.
 * @param name the name of the hypernym
 * @return a hypernym object.
 */
    public Hypernym getHypernym(String name) {
        if (this.relationMap.containsKey(name)) {
            return this.relationMap.get(name);
        }
        Hypernym hypernym = new Hypernym(name);
        this.relationMap.put(name, hypernym);
        return hypernym;
    }
/**
 * getter for the relation Map that keeps the hypernyms.
 * @return the map object that keeps the hypernyms
 */
    protected Map<String, Hypernym> getRelationMap() {
        return this.relationMap;
    }
/**
 * getter for the list of patterns the hypernyms are looking for.
 * @return the list that keeps the patterns.
 */
    protected List<HearstPattern> getPatterns() {
    return this.patterns;
    }
/**
 * method that gets a buffered reader that can read lines and processes it looking for patterns.
 * @param reader the reader that gets the lines to read from.
 * @throws IOException if there is a problem during the reading procces.
 */
    public void readData(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) { // ’null ’->no more data in the stream
            if (line.isEmpty()) { //if the line is empty skips it
                continue;
            }
            this.getRelationFromLine(line);

        }
    }
/**
 * insert a new hypernym and hyponym relation into the database.
 * @param hypernym a hypernym to relate to.
 * @param hyponym a hyponym to relate
 */
    public void insert(Hypernym hypernym, NounPhrase hyponym) {
        if (!this.relationMap.containsKey(hypernym.getName())) {
            this.relationMap.put(hypernym.getName(), hypernym);
        }
        hypernym.relate(hyponym);
    }
/**
 * get a line and check for hearst patterns in it.
 * if there is a pattern gets the hypernym and its hyponym and adds it to the databse.
 * @param line the line to read for
 */
    public void getRelationFromLine(String line) {
        line = line.toLowerCase();
        for (HearstPattern pattern : this.patterns) {
            List<String> patternStrings = pattern.getPatternStrings(line);
            if (!patternStrings.isEmpty()) {
                for (String ps : patternStrings) {
                    List<String> matches = pattern.getMatchesList(ps);
                    if (!matches.isEmpty()) {
                        matches = pattern.removeTags(matches);
                        Hypernym hypernym = pattern.getHypernym(this, matches);
                        List<NounPhrase> hyponyms = pattern.getHyponymList(matches);
                        for (NounPhrase hyponym : hyponyms) {
                            this.insert(hypernym, hyponym);
                        }
                    }
                }
            }
        }

    }
/**
 * export the database.
 * @param os Output Stream Writer.
 * @throws IOException if there is a problem while writing.
 */
    public void export(OutputStreamWriter os) throws IOException {
        for (Map.Entry<String, Hypernym> hypernyms : relationMap.entrySet()) {
            hypernyms.getValue().sortByOccurrences();
            os.write(hypernyms.getValue().toString() + "\n");
        }
        os.close();
    }
/**
 * filters the hypernyms that have under 3 hyponyms.
 */
    public void filterHypernyms() {
        Map<String, Hypernym> hypernyms = new TreeMap<>(this.relationMap);
        for (Map.Entry<String, Hypernym> entry : hypernyms.entrySet()) {
            if (entry.getValue().getNumOfHyponyms() < 3) {
                this.relationMap.remove(entry.getKey());
            }
        }
    }
/**
 * creation of a pattern objects.
 * @return all the pattern objects.
 */
    private static List<HearstPattern> createPatterns() {
        List<HearstPattern> patterns = new ArrayList<>();
        patterns.add(new SuchAs1Pattern());
        patterns.add(new SuchAs2Pattern());
        patterns.add(new IncludingPattern());
        patterns.add(new EspeciallyPattern());
        patterns.add(new ExampleOfPattern());
        return patterns;
    }
}