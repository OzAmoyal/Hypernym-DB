import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * a class for a Hearst pattern containing *noun phrase* such as *another noun
 * phrases*.
 * @author ozamoyal
 */
public class SuchAs1Pattern extends HearstPattern {
    private RegexMatcher matcher;
    public static final String KEYWORD = "such";
    public static final String SUCH_AS1 = NP + "(,| )*such( )*as( )*" + NP + "(( |,)*" + NP + ")*(( |,)*(and|or)( )*"
            + NP + ")?";

    /**
     * a constructor for the pattern that initializes the regexmatcher with the
     * regex string.
     */
    public SuchAs1Pattern() {
        Pattern pattern = Pattern.compile(SUCH_AS1);
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
