package tr1fker.statement.dao;

import tr1fker.connection.ConnectionManager;
import tr1fker.statement.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
public class BookDao implements StatementDao<Book> {
    private static final Logger logger = Logger.getLogger(BookDao.class.getName());
    @Override
    public void clearTable() {
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            stmt.executeUpdate("DELETE FROM books");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public void insert(Book book) {
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            stmt.executeUpdate("INSERT INTO books (title, author_id, year_of_publication) " +
                    "VALUES ('" + book.getTitle() + "', '" + book.getAuthorId() + "', '"
                            + book.getYearOfPublication() + "')");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getCount() {
        int count = 0;
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM books");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return count;
    }
    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthorId(resultSet.getLong("author_id"));
                book.setYearOfPublication(resultSet.getInt("year_of_publication"));
                books.add(book);
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return books;
    }

    public List<Book> getSrtYByYear(int year){
        List<Book> books = new ArrayList<>();
        try (Statement stmt = ConnectionManager.getConnection().createStatement()){
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books WHERE year_of_publication > "
                    + year + " ORDER BY year_of_publication");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthorId(resultSet.getLong("author_id"));
                book.setYearOfPublication(resultSet.getInt("year_of_publication"));
                books.add(book);
            }
        }catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Book getById(Long id) {
        Book book = null;
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books WHERE id = " +
                    id);
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthorId(resultSet.getLong("author_id"));
                book.setYearOfPublication(resultSet.getInt("year_of_publication"));
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return book;
    }

    public int updateNameById(long id, String name){
        try(Statement stmt = ConnectionManager.getConnection().createStatement()){
            return stmt.executeUpdate("UPDATE books SET title = '" + name + "' WHERE id = '" + id + "'");
        }catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}