package by.tr1fker;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Book> books;

    public Catalog(){
        books = new ArrayList<Book>();
    }


    public void push(Book book){
        books.add(book);
    }

    @Override
    public String toString() {
        return this.books.toString();
    }
}
