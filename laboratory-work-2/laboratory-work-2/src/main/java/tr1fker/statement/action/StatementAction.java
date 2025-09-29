package tr1fker.statement.action;

import tr1fker.statement.dao.AuthorDao;
import tr1fker.statement.dao.BookDao;
import tr1fker.statement.dao.ShopBookDao;
import tr1fker.statement.dao.ShopDao;
import tr1fker.statement.model.Author;
import tr1fker.statement.model.Book;
import tr1fker.statement.model.Shop;
import tr1fker.statement.model.ShopBook;
import tr1fker.utils.InputManager;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
public class StatementAction {
    private static final Logger logger =
            Logger.getLogger(StatementAction.class.getName());
    AuthorDao authorDao = new AuthorDao();
    BookDao bookDao = new BookDao();
    ShopDao shopDao = new ShopDao();
    ShopBookDao shopBookDao = new ShopBookDao();
    public void deleteAllInfo() {
        System.out.print("Подтвердите удаление всей информации (y / Y): ");
        final String choice = InputManager.getNextLine();
        if (!choice.equals("Y") && !choice.equals("y")) {
            System.out.println("Удаление было отменено!");
            return;
        }
        try {
            shopBookDao.clearTable();
            bookDao.clearTable();
            authorDao.clearTable();
            shopDao.clearTable();
            System.out.println("Информация из таблиц была успешно удалена!");
        } catch (RuntimeException e) {
            logger.severe("При удалении информации из таблиц ошибка: " + e.getMessage());
        }
    }
    public void addDefaultInfo() {
        try {
            if (authorDao.getCount() != 0 || bookDao.getCount() != 0 ||
                    shopDao.getCount() != 0 || shopBookDao.getCount() != 0) {
                System.out.println("Таблицы не пусты. Вставка информации по умолчанию невозможна!");
                return;
            }
            List<Author> authors = getDefaultAuthors();
            for (Author author: authors) {
                authorDao.insert(author);
            }
            authors = authorDao.getAll();
            List<Book> books = getDefaultBooks(authors);
            for (Book book: books) {
                bookDao.insert(book);
            }
            books = bookDao.getAll();
            List<Shop> shops = getDefaultShops();
            for (Shop shop: shops) {
                shopDao.insert(shop);
            }
            shops = shopDao.getAll();
            List<ShopBook> shopBooks = getDefaultShopBooks(shops, books);
            for (ShopBook shopBook: shopBooks) {
                shopBookDao.insert(shopBook);
            }
            System.out.println("Информация по умолчанию вставлена успешно!");
        } catch (RuntimeException e) {
            logger.severe("При добавлении информации в таблицы возникла ошибка: " +
                    e.getMessage());
        }
    }
    public void getAllInfo() {
        try {
            List<Author> authors = authorDao.getAll();
            if (authors.isEmpty()) {
                System.out.println("Таблица авторов пуста!");
            } else {
                System.out.println("[Авторы]");
                for (Author author: authors) {
                    System.out.println(author);
                }
            }

            List<Book> books = bookDao.getAll();
            if (books.isEmpty()){
                System.out.println("Таблица книг пуста!");
            }else{
                System.out.println("[Книги]");
                for (Book book : books){
                    System.out.println(book);
                }
            }

            List<Shop> shops = shopDao.getAll();
            if (shops.isEmpty()) {
                System.out.println("Таблица магазинов пуста!");
            }else{
                System.out.println("[Магазины]");
                for (Shop shop: shops){
                    System.out.println(shop);
                }
            }

            List<ShopBook> shopBooks = shopBookDao.getAll();
            if (shopBooks.isEmpty()) {
                System.out.println("Таблица книг в магазинах пуста!");
            }else{
                System.out.println("[Книги в магазинах]");
                for (ShopBook shopBook: shopBooks){
                    System.out.println(shopBook);
                }
            }
        } catch (RuntimeException e) {
            logger.severe("При просмотре информации возникла ошибка: " + e.getMessage());
        }
    }

    public void getAuthorsInfSrtFNLN(){
        try {
            List<Author> authors = authorDao.getSortedFNLN();
            if (authors.isEmpty()){
                System.out.println("Таблица авторов пуста!");
            }else{
                System.out.println("[Авторы]");
                for (Author author: authors){
                    System.out.println(author);
                }
            }
        }catch (RuntimeException e){
            logger.severe("При просмотре отсортированных авторов по имени и фамилии произошла ошибка: " +
                    e.getMessage());
        }
    }

    public void getBooksSrtYByYear(){
        int year;
        while(true){
            System.out.print("Введите год для поиска: ");
            year = InputManager.getNextInt();
            if (year < 0){
                System.out.println("Год не может быть отрицательным!");
            }else{
                break;
            }
        }

        List<Book> books = bookDao.getSrtYByYear(year);
        if (books.isEmpty()){
            System.out.println("Книги с указанным " + year + " не найдены!");
            return;
        }
        System.out.println("[Книги по возрастанию году публикации]");
        for (Book book: books){
            System.out.println(book);
        }
    }

    private List<Author> getDefaultAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Лев", "Толстой"));
        authors.add(new Author("Фёдор", "Достоевский"));
        authors.add(new Author("Александр", "Пушкин"));
        authors.add(new Author("Антон", "Чехов"));
        return authors;
    }
    private List<Book> getDefaultBooks(List<Author> authors) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(
                "Война и мир",
                authors.stream().filter(x ->
                        x.getLastName().equals("Толстой")).findFirst().orElseThrow().getId(),
                1869
        ));
        books.add(new Book(
                "Анна Каренина",
                authors.stream().filter(x ->
                        x.getLastName().equals("Толстой")).findFirst().orElseThrow().getId(),
                1877
        ));
        books.add(new Book(
                "Преступление и наказание",
                authors.stream().filter(x ->
                        x.getLastName().equals("Достоевский")).findFirst().orElseThrow().getId(),
                1866
        ));
        books.add(new Book(
                "Идиот",
                authors.stream().filter(x ->
                        x.getLastName().equals("Достоевский")).findFirst().orElseThrow().getId(),
                1869
        ));
        books.add(new Book(
                "Евгений Онегин",
                authors.stream().filter(x ->
                        x.getLastName().equals("Пушкин")).findFirst().orElseThrow().getId(),
                1833
        ));
        books.add(new Book(
                "Капитанская дочка",
                authors.stream().filter(x ->
                        x.getLastName().equals("Пушкин")).findFirst().orElseThrow().getId(),
                1836
        ));
        books.add(new Book(
                "Чайка",
                authors.stream().filter(x ->
                        x.getLastName().equals("Чехов")).findFirst().orElseThrow().getId(),
                1896
        ));
        books.add(new Book(
                "Вишнёвый сад",
                authors.stream().filter(x ->
                        x.getLastName().equals("Чехов")).findFirst().orElseThrow().getId(),
                1904
        ));
        return books;
    }
    private List<Shop> getDefaultShops() {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(
                "Белкнига",
                "проспект Независимости, 14"
        ));
        shops.add(new Shop(
                "Букинист",
                "проспект Независимости, 53"
        ));
        shops.add(new Shop(
                "Oz",
                "улица Сурганова, 21"
        ));
        return shops;
    }
    private List<ShopBook> getDefaultShopBooks(List<Shop> shops, List<Book> books) {
        List<ShopBook> shopBooks = new ArrayList<>();
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Белкнига")).findFirst().orElseThrow().getId(),
                books.stream().filter(x -> x.getTitle().equals("Война и " +
                        "мир")).findFirst().orElseThrow().getId()
                ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Белкнига")).findFirst().orElseThrow().getId(),
                books.stream().filter(x ->
                        x.getTitle().equals("Чайка")).findFirst().orElseThrow().getId()
        ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Белкнига")).findFirst().orElseThrow().getId(),
                books.stream().filter(x -> x.getTitle().equals("Капитанская " +
                        "дочка")).findFirst().orElseThrow().getId()
                ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Букинист")).findFirst().orElseThrow().getId(),
                books.stream().filter(x -> x.getTitle().equals("Анна " +
                        "Каренина")).findFirst().orElseThrow().getId()
                ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Букинист")).findFirst().orElseThrow().getId(),
                books.stream().filter(x ->
                        x.getTitle().equals("Идиот")).findFirst().orElseThrow().getId()
        ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Букинист")).findFirst().orElseThrow().getId(),
                books.stream().filter(x -> x.getTitle().equals("Евгений " +
                        "Онегин")).findFirst().orElseThrow().getId()
                ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Oz")).findFirst().orElseThrow().getId(),
                books.stream().filter(x -> x.getTitle().equals("Преступление и " +
                        "наказание")).findFirst().orElseThrow().getId()
                ));
        shopBooks.add(new ShopBook(
                shops.stream().filter(x ->
                        x.getName().equals("Oz")).findFirst().orElseThrow().getId(),
                books.stream().filter(x -> x.getTitle().equals("Вишнёвый " +
                        "сад")).findFirst().orElseThrow().getId()
                ));
        return shopBooks;
    }
}