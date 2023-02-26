import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * a class that keeps the hypernyms of a given lemma.
 * scans text and gets all hypernyms into the database.
 */
public class LemmaHypernymDatabase extends HypernymDatabase {
    private NounPhrase lemma;

    /**
     * constructor for a database getting a lemma to search it's hypernyms.
     * @param lemma the lemma that needs to be checked.
     */
    public LemmaHypernymDatabase(NounPhrase lemma) {
        super();
        this.lemma = lemma;
    }

    @Override
    public void getRelationFromLine(String line) {
        line = line.toLowerCase();
        if (!line.contains(this.lemma.getName())) {
            return;
        }
        for (HearstPattern pattern : this.getPatterns()) {
            List<String> patternStrings = pattern.getPatternStrings(line);
            if (!patternStrings.isEmpty()) {
                for (String ps : patternStrings) {
                    List<String> matches = pattern.getMatchesList(ps);
                    if (!matches.isEmpty()) {
                        matches = pattern.removeTags(matches);

                        List<NounPhrase> hyponyms = pattern.getHyponymList(matches);
                        if (hyponyms.contains(this.lemma)) {
                            Hypernym hypernym = pattern.getHypernym(this, matches);
                            this.insert(hypernym, this.lemma);

                        }
                    }
                }
            }
        }

    }

    @Override
    public void export(OutputStreamWriter os) throws IOException {
        if (this.getRelationMap().isEmpty()) {
            System.out.println("The lemma doesn't appear in the corpus.");
            return;
        }
        // wrapper that can write strings
        List<Hypernym> hypernyms = new ArrayList<>(this.getRelationMap().values());
        hypernyms.sort(new Comparator<Hypernym>() {

            @Override
            public int compare(Hypernym o1, Hypernym o2) {
                int x = o1.getMaxOccurence();
                int y = o2.getMaxOccurence();
                if (x < y) {
                    return 1;
                } else if (x == y) {
                    return 0;
                } else {
                    return -1;
                }
            }

        });

        for (Hypernym hypernym : hypernyms) {
            os.write(hypernym.getName() + ": (" + hypernym.getMaxOccurence() + ")\n");
        }
        os.close();
    }
}
