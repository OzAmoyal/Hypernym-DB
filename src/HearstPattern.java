import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/**
 * an abstract class for Hearst Patterns that helps extract nounphrases that fit the pattern.
 * every specific pattern should implement the abstract method getPatternStrings, and to do so it needs to create a
 * regex pattern and match the line according to it.
 * @author ozamoyal
 */
public abstract class HearstPattern {
    private RegexMatcher npMatcher;
    public static final String NP = "<np>([^<])*</np>";
    /**
     * constructor for a hearset pattern that creates it a regexMatcher to get the NP tags.
     */
    public HearstPattern() {
        Pattern pattern = Pattern.compile(NP);
        this.npMatcher = new RegexMatcher(pattern);

    }
/**
 * a method that gets Strings of matches that fit the pattern and removes the np tags from them.
 * @param matches the matching nounPharses that are found in the pattern.
 * @return a new list of the same matches without the tags.
 */
    public ArrayList<String> removeTags(List<String> matches) {
        ArrayList<String> clean = new ArrayList<>();
        for (String match : matches) {

            match = match.replace("<np>", "");
            match = match.replace("</np>", "");
            clean.add(match);
        }
        return clean;

    }
    /**
     * a method that gets a line and returns the matching patterns that are found in the string.
     * @param line the line to look for
     * @return a list of strings with matches of the pattern.
     */
    public abstract List<String> getPatternStrings(String line);
/**
 * a method that gets a pattern string that was found and extracts the noun pharses string from it.
 * @param line the matching Pattern String.
 * @return a list of Noun phrases from the match
 */
    public List<String> getMatchesList(String line) {
        return this.npMatcher.findMatches(line);
    }
/**
 * a method that returns the hypernym of the nounPhrase list and adds it to the hypernym database.
 * by default takes the first noun phrase to be the Hypernym.
 * @param db the hypernym database
 * @param matches the list of nounphrase matches.
 * @return a hypernym object from the pattern.
 */
    public Hypernym getHypernym(HypernymDatabase db, List<String> matches) {
        return db.getHypernym(matches.get(0));
    }
/**
 * a method that returns the hyponyms of the pattern
 * by default takes everyone but the first in the matches array to be a hyponym.
 * @param matches the strings of the nounphrases from the pattern.
 * @return a list of nounphrases object from the pattern.
 */
    public List<NounPhrase> getHyponymList(List<String> matches) {
        List<NounPhrase> nounPhrases = new ArrayList<>();
        for (int i = 1; i < matches.size(); i++) {
            nounPhrases.add(NounPhrase.getNounPhrase(matches.get(i)));
        }
        return nounPhrases;
    }

}
