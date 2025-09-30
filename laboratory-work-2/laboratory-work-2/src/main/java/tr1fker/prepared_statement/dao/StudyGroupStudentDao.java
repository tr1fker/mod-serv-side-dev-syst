package tr1fker.prepared_statement.dao;

import tr1fker.connection.ConnectionManager;
import tr1fker.prepared_statement.model.StudyGroup;
import tr1fker.prepared_statement.model.StudyGroupStudent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudyGroupStudentDao implements PreparedStatementDao<StudyGroupStudent>{
    private static final Logger logger = Logger.getLogger(StudyGroupStudentDao.class.getName());
    @Override
    public StudyGroupStudent getById(Long id) {
        StudyGroupStudent studyGroupStudent = null;
        final String query = "SELECT * FROM study_group_members WHERE id = ? LIMIT 1";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, id);
            ResultSet resultSet = pStmt.executeQuery();
            if (resultSet.next()) {
                studyGroupStudent = new StudyGroupStudent();
                studyGroupStudent.setId(resultSet.getLong("id"));
                studyGroupStudent.setId(resultSet.getLong("study_group_id"));
                studyGroupStudent.setId(resultSet.getLong("student_id"));
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return studyGroupStudent;
    }
    @Override
    public void insert(StudyGroupStudent studyGroupStudent) {
        final String query = "INSERT INTO study_group_members (study_group_id, student_id)" +
                " VALUES (?, ?)";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, studyGroupStudent.getGroupId());
            pStmt.setLong(2, studyGroupStudent.getStudentId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(StudyGroupStudent studyGroupStudent) {
        final String query = "UPDATE study_group_members " +
                "SET study_group_id = ?, student_id = ? " +
                "WHERE id = ?";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, studyGroupStudent.getGroupId());
            pStmt.setLong(2, studyGroupStudent.getStudentId());
            pStmt.setLong(3, studyGroupStudent.getId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(Long id) {
        final String query = "DELETE FROM study_group_members WHERE id = ?";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, id);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<StudyGroupStudent> getAll() {
        List<StudyGroupStudent> studyGroupStudents;
        final String query = "SELECT * FROM study_group_members";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = pStmt.executeQuery();
            studyGroupStudents = getStudyGroupStudentsListByResultSet(resultSet);
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return studyGroupStudents;
    }
    private List<StudyGroupStudent> getStudyGroupStudentsListByResultSet(ResultSet resultSet) throws
            SQLException {
        List<StudyGroupStudent> studyGroupStudents = new ArrayList<>();
        while (resultSet.next()) {
            StudyGroupStudent studyGroupStudent = new StudyGroupStudent();
            studyGroupStudent.setId(resultSet.getLong("id"));
            studyGroupStudent.setId(resultSet.getLong("study_group_id"));
            studyGroupStudent.setId(resultSet.getLong("student_id"));
            studyGroupStudents.add(studyGroupStudent);
        }
        return studyGroupStudents;
    }
    public int getCount() {
        int count = 0;
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM study_group_members");
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return count;
    }
    public void clearTable() {
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            stmt.executeUpdate("DELETE FROM study_group_members");
            stmt.executeUpdate("ALTER SEQUENCE study_group_members_id_seq RESTART WITH 1");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
