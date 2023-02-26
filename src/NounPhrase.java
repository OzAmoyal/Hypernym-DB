import java.util.Map;
import java.util.TreeMap;
/**
 * a class for noun pharases that creates signleton objects(no doubles) of noun pharses.
 * creation of instance is only through a static getNounPharse method. the singletons are stored in a static map,
 * and this way there are no double singletons.
 * @author ozamoyal
 */
public final class NounPhrase implements Comparable<NounPhrase> {
    private String name;
    private static Map<String, NounPhrase> nounPhraseDB = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private NounPhrase(String name) {
        this.name = name;
    }
    /**
     * method for getting an instance of a NounPhrase object signleton.
     * checks if one with the same name was created and if not, creates it adds it to the database and reutrns it.
     * @param name the name of the noun phrase
     * @return a noun pharse instance with the name.(no doubles).
     */
    public static NounPhrase getNounPhrase(String name) {
        if (nounPhraseDB.containsKey(name)) {
            return nounPhraseDB.get(name);
        }
        NounPhrase np = new NounPhrase(name);
        nounPhraseDB.put(name, np);
        return np;
    }
/**
 * getter method for the name of the nounphrase.
 * @return the name of the nounphrase.
 */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(NounPhrase np) {
        return this.name.compareTo(np.getName());
    }

}
