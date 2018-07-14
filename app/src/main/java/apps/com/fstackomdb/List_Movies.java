package apps.com.fstackomdb;

public class List_Movies {

    String stringMovieName,stringMovieImage,stringMovieId,stringMovieYear;

    public List_Movies(){
        this.stringMovieId = "";
        this.stringMovieName = "";
        this.stringMovieImage = "";
        this.stringMovieYear = "";
    }

    public void setStringMovieId(String stringMovieId) {
        this.stringMovieId = stringMovieId;
    }

    public String getStringMovieId() {
        return stringMovieId;
    }

    public void setStringMovieImage(String stringMovieImage) {
        this.stringMovieImage = stringMovieImage;
    }

    public String getStringMovieImage() {
        return stringMovieImage;
    }

    public void setStringMovieName(String stringMovieName) {
        this.stringMovieName = stringMovieName;
    }

    public String getStringMovieName() {
        return stringMovieName;
    }

    public void setStringMovieYear(String stringMovieYear) {
        this.stringMovieYear = stringMovieYear;
    }

    public String getStringMovieYear() {
        return stringMovieYear;
    }
}
