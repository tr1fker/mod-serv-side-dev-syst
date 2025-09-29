package tr1fker.statement.model;

import lombok.Data;
@Data
public class Author {
    private Long id;
    private String firstName;
    private String lastName;
    public Author() {}
    public Author(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}