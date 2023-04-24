package enties;

import lombok.*;

@NoArgsConstructor


public class Books {


    public Integer id;
    public String title;
    public String authorName;
    public Integer edited;
    public Integer numberOfbooks;


    public Books(Integer id, String title, String authorName, Integer edited, Integer numberOfbooks) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.edited = edited;
        this.numberOfbooks = numberOfbooks;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", edited=" + edited +
                ", numberOfbooks=" + numberOfbooks +
                '}';
    }
}
