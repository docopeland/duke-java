
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (Danielle) 
 * @version (7 August 2020)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String fname;
    public PhraseFilter(String whe, String phr, String name) {
        where = whe;
        phrase = phr;
        fname = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start") && qe.getInfo().startsWith(phrase)) {
            return true;
        }
        if (where.equals("end") && qe.getInfo().endsWith(phrase)) {
            return true;
        }
        if (where.equals("any") && qe.getInfo().contains(phrase)) {
            return true;
        }
        return false;
    }
    public String getName() {
        return fname;
    }
}
