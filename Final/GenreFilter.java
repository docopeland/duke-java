public class GenreFilter implements Filter {
    private String myGenre;
    public GenreFilter(String genre) {
        myGenre = genre;
    }
    @Override
    public boolean satisfies(String id) {
        String[] genres = MovieDatabase.getGenres(id).split(",\\s");
        for (int k = 0; k < genres.length; k++) {
            if (genres[k].endsWith(myGenre)) {
                return true;
            }
        }
        return false;
    }
}
