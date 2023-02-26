import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * a class for a Hearst pattern containing *noun phrase* that is especially *another noun phrases*.
 * @author ozamoyal
 */
public class EspeciallyPattern extends HearstPattern {
    private RegexMatcher matcher;
    public static final String KEYWORD = "especially";
    public static final String ESPECIALLY = NP + "(,| )*especially(,| )*" + NP + "((,| )*" + NP
            + ")*((,| )*(and|or)( )*" + NP + ")?";
/**
 * a constructor for the pattern that initializes the regexmatcher with the regex string.
 */
    public EspeciallyPattern() {
        super();
        Pattern pattern = Pattern.compile(ESPECIALLY);
        this.matcher = new RegexMatcher(pattern);
    }

    @Override
    public List<String> getPatternStrings(String line) {
        if (line.contains(KEYWORD)) {
            return this.matcher.findMatches(line);
        } else {
            return new ArrayList<>();
        }
    }

}