import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * a class for a Hearst pattern containing such *noun phrase* as *another noun
 * phrases*.
 * @author ozamoyal
 */
public class SuchAs2Pattern extends HearstPattern {
    private RegexMatcher matcher;
    public static final String KEYWORD = "such";
    public static final String SUCH_AS2 = "such(,| )*" + NP + "(,| )*as(,| )*" + NP + "((,| )*" + NP
            + ")*((,| )*(and|or)( )*" + NP + ")?";
/**
 * a constructor for the pattern that initializes the regexmatcher with the regex string.
 */
    public SuchAs2Pattern() {
        super();
        Pattern pattern = Pattern.compile(SUCH_AS2);
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