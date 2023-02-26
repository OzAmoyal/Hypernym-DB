import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * a class that helps find matches of a regex pattern.
 * @author ozamoyal
 */
public class RegexMatcher {
    private Pattern pattern;
/**
 * constructor for a object getting a match.
 * @param pattern the pattern to match.
 */
    public RegexMatcher(Pattern pattern) {
        this.pattern = pattern;
    }
    /**
     * method that gets a string line and finds matches of the pattern in it and puts it in an arraylist.
     * @param line the line to read
     * @return an arraylist with matches of the pattern.
     */
    public ArrayList<String> findMatches(String line) {
        ArrayList<String> matches = new ArrayList<>();
        Matcher mathcer = pattern.matcher(line);
        while (mathcer.find()) {
            matches.add(line.substring(mathcer.start(), mathcer.end()));
        }
        return matches;
    }

}
