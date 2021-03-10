package mandatory_assignment.jesterday.objects;

public class Joke {

    private String title;
    private String content;
    private String date;

    private boolean isPublic;
    private boolean isPoliticalCorrect;

    public Joke(String title, String content, String date, boolean isPublic, boolean isPoliticalCorrect) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.isPublic = isPublic;
        this.isPoliticalCorrect = isPoliticalCorrect;
    }

    @Override
    public String toString() {
        if (isPublic) {
            return title + "   " + content;
        }
        else {
            return null;
        }
    }

}
