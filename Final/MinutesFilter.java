public class MinutesFilter implements Filter {
    private int minMin;
    private int maxMin;
    public MinutesFilter(int min, int max) {
        minMin = min;
        maxMin = max;
    }
    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
        if (minutes >= minMin && minutes <= maxMin) {
            return true;
        }
        return false;
    }
}
