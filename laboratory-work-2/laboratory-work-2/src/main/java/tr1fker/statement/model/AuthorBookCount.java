package tr1fker.statement.model;

import lombok.Data;

@Data
public class AuthorBookCount {
    private Author author;
    private int bookCount;

    public AuthorBookCount(Author author, int bookCount) {
        this.author = author;
        this.bookCount = bookCount;
    }
}