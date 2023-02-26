import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * a class for a Hearst pattern containing *noun phrase* including *another noun
 * phrases*.
 * @author ozamoyal
 */
public class IncludingPattern extends HearstPattern {
    private RegexMatcher matcher;
    public static final String KEYWORD = "including";
    public static final String INCLUDING = NP + "(,| )*including( )*" + NP + "((,| )*" + NP + ")*((,| )*(and|or)( )*"
            + NP + ")?";
/**
 * a constructor for the pattern that initializes the regexmatcher with the regex string.
 */
    public IncludingPattern() {
        super();
        Pattern pattern = Pattern.compile(INCLUDING);
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