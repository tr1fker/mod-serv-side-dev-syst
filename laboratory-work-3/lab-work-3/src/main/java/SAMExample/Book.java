package SAMExample;


import java.util.Objects;

public class Book {
    private String id;
    private String author;
    private String title;
    private String genre;
    private Double price;
    private String publishDate;
    private String description;

    // Конструктор по умолчанию
    public Book() {
    }

    // Конструктор с параметрами
    public Book(String id, String author, String title, String genre, Double price, String
            publishDate, String description) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
        this.description = description;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Переопределение equals() для корректного сравнения объектов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(price, book.price) &&
                Objects.equals(publishDate, book.publishDate) &&
                Objects.equals(description, book.description);
    }

    // Переопределение hashCode() для использования в коллекциях
    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, genre, price, publishDate, description);
    }

    // Переопределение toString() для удобного отображения объекта
    @Override
    public String toString() {
        return """ 
           
           ╔═══════════════════════════════════╗ 
           ║      Информация о книге           ║ 
           ╚═══════════════════════════════════╝ 
           ID:               %s 
           Автор:            %s 
           Наименование:     %s 
           Жанр:             %s 
           Цена:             %s 
           Дата публикации:  %s 
           Описание:         %s 
           """.formatted(
                id != null ? id : "N/A",
                author != null ? author : "N/A",
                title != null ? title : "N/A",
                genre != null ? genre : "N/A",
                price != null ? String.format("$%.2f", price) : "N/A",
                publishDate != null ? publishDate : "N/A",
                description != null ? description : "N/A"
        );
    }
}