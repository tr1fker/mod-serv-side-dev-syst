package tr1fker.statement.dao;

import tr1fker.connection.ConnectionManager;
import tr1fker.statement.model.Author;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
public class AuthorDao implements StatementDao<Author> {
    private static final Logger logger = Logger.getLogger(AuthorDao.class.getName());
    @Override
    public void clearTable() {
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            stmt.executeUpdate("DELETE FROM authors");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public void insert(Author author) {
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            stmt.executeUpdate("INSERT INTO authors (first_name, last_name) " +
                    "VALUES ('" + author.getFirstName() + "', '" + author.getLastName() +
                    "')");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getCount() {
        int count = 0;
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM authors");
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
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM authors");
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                authors.add(author);
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return authors;
    }

    public List<Author> getSortedFNLN(){
        List<Author> authors = new ArrayList<>();
        try (Statement stmt = ConnectionManager.getConnection().createStatement()){
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM authors ORDER BY first_name, last_name");
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                authors.add(author);
            }
        }catch (SQLException e){
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return authors;
    }

    @Override
    public Author getById(Long id) {
        Author author = null;
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM authors WHERE id = " +
                    id);
            if (resultSet.next()) {
                author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return author;
    }

    public int updateByShopId(long shopId, String newFirstName){
        try(Statement stmt = ConnectionManager.getConnection().createStatement()){
            return stmt.executeUpdate("UPDATE authors SET first_name = '" + newFirstName + "' " +
                    "WHERE id IN (SELECT DISTINCT b.author_id FROM books b " +
                    "JOIN shops_books sb ON b.id = sb.book_id WHERE sb.shop_id = '" + shopId + "')");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}