
public class WordGram {
    private String[] myWords;
    private int myHash;
    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }
    public int length(){
        return myWords.length;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < myWords.length; k++) {
            sb.append(myWords[k]);
            sb.append(" ");
        }
        return sb.toString();
    }
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int k = 0; k < myWords.length; k++) {
            if (! myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;
    }
    public int hashCode() {
        int myHash = toString().hashCode();
        return myHash;
    }
    public WordGram shiftAdd(String word) {
        WordGram out = new WordGram(myWords, 0, myWords.length);
        String [] shift = new String[length()];
        for (int k = 1; k < length(); k++) {
            shift[k-1] = out.wordAt(k);
        }
        shift[length()-1] = word;
        WordGram newOut = new WordGram(shift, 0, myWords.length);
        return newOut;
    }
}