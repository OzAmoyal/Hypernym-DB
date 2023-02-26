import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
/**
 * a class for hypernym object that holds all of the hyponyms related to it in a map.
 * @author ozamoyal
 */
public class Hypernym {
    private NounPhrase np;
    private Map<NounPhrase, Integer> hyponyms;
/**
 * a constructor for a hypernym object getting its name.
 * @param name the name of the hypernym.
 */
    public Hypernym(String name) {
        this.np = NounPhrase.getNounPhrase(name);
        hyponyms = new TreeMap<NounPhrase, Integer>();
    }
/**
 * getter for the hypernym name.
 * @return the name of the hypernym.
 */
    public String getName() {
        return this.np.getName();
    }
/**
 * relates a noun phrase to the hypernym, if they are already related updates to its counter.
 * @param hyponym the noun pharse that is related to the hypernym.
 */
    public void relate(NounPhrase hyponym) {
        if (this.hyponyms.containsKey(hyponym)) {
            this.hyponyms.put(hyponym, this.hyponyms.get(hyponym) + 1);
            return;
        }
        this.hyponyms.put(hyponym, 1);
    }
/**
 * a method that returns the number of the hyponyms the hypernym is related to..
 * @return the number of hyponyms.
 */
    public int getNumOfHyponyms() {
        return hyponyms.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName() + ": ");
        for (Map.Entry<NounPhrase, Integer> hyponym : hyponyms.entrySet()) {
            sb.append(hyponym.getKey().toString() + " (" + hyponym.getValue().toString() + "), ");
        }
        int lastComma = sb.lastIndexOf(",");
        if (lastComma != -1) {
            sb.replace(lastComma, lastComma + 1, "");
        }
        return sb.toString();
    }
/**
 * sorts the hyponyms by their values.
 */
    public void sortByOccurrences() {
        this.hyponyms = sortValues(this.hyponyms);
    }
/**
 * get the maximum number of occurences of a hyponym.
 * @return the number of maximum occured hyponym.
 */
    public int getMaxOccurence() {
        int max = 1;
        for (Map.Entry<NounPhrase, Integer> hyponym : hyponyms.entrySet()) {
            if (hyponym.getValue() > max) {
                max = hyponym.getValue();
            }
        }
        return max;

    }
   /**
    * gets a map of nounphrase and integer and sorts it in descending order of the integer values
    * if values are equal sorts by lexicographic values.
    * got refrence in https://www.flowerbrackets.com/java-treemap-sort-by-value/
    * changed it for the demeand of descending order->lexicographic order.
    * @param m the map that needs to be sorted.
    * @return the sorted map.
    */
   public static Map<NounPhrase, Integer> sortValues(Map<NounPhrase, Integer> m) {
    Comparator<NounPhrase> com = new Comparator<NounPhrase>() {
       public int compare(NounPhrase np1, NounPhrase np2) {
          int compare = m.get(np1).compareTo(m.get(np2));
          if (compare == 0) {
             return np1.compareTo(np2);
          } else {
             return -compare;
          }
       }
    };
    Map<NounPhrase, Integer> sortedByValues = new TreeMap<NounPhrase, Integer>(com);
    sortedByValues.putAll(m);
    return sortedByValues;
 }

}
