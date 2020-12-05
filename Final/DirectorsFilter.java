public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }
    @Override
    public boolean satisfies(String id) {
        String[] directors = MovieDatabase.getDirector(id).split(",\\s");
        for (int i = 0; i < directors.length; i++) {
            for (int k = 0; k < myDirectors.length; k++) {
                if (directors[i].equals(myDirectors[k])) {
                    return true;
                }
            }
        }
        return false;
    }
}
