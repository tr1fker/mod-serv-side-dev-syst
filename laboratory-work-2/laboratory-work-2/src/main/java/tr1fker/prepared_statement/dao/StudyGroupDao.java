package tr1fker.prepared_statement.dao;

import tr1fker.connection.ConnectionManager;
import tr1fker.prepared_statement.model.Student;
import tr1fker.prepared_statement.model.StudyGroup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StudyGroupDao implements PreparedStatementDao<StudyGroup> {
    private static final Logger logger = Logger.getLogger(StudyGroupDao.class.getName());
    @Override
    public StudyGroup getById(Long id) {
        StudyGroup studyGroup = null;
        final String query = "SELECT * FROM study_groups WHERE id = ? LIMIT 1";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setLong(1, id);
            ResultSet resultSet = pStmt.executeQuery();
            if (resultSet.next()) {
                studyGroup = new StudyGroup();
                studyGroup.setId(resultSet.getLong("id"));
                studyGroup.setName(resultSet.getString("name"));
                studyGroup.setDescription(resultSet.getString("description"));
                studyGroup.setSubjectArea(resultSet.getString("subject_area"));
                studyGroup.setMeetingSchedule(resultSet.getString("meeting_schedule"));
            }
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return studyGroup;
    }
    @Override
    public void insert(StudyGroup studyGroup) {
        final String query = "INSERT INTO study_groups (group_name, description, subject_area, meeting_schedule)" +
                " VALUES (?, ?, ?, ?)";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setString(1, studyGroup.getName());
            pStmt.setString(2, studyGroup.getDescription());
            pStmt.setString(3, studyGroup.getSubjectArea());
            pStmt.setString(4, studyGroup.getMeetingSchedule());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(StudyGroup studyGroup) {
        final String query = "UPDATE study_groups " +
                "SET group_name = ?, description = ?, subject_area = ?, meeting_schedule = ? " +
                "WHERE id = ?";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            pStmt.setString(1, studyGroup.getName());
            pStmt.setString(2, studyGroup.getDescription());
            pStmt.setString(3, studyGroup.getSubjectArea());
            pStmt.setString(4, studyGroup.getMeetingSchedule());
            pStmt.setLong(5, studyGroup.getId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(Long id) {
        final String query = "DELETE FROM study_groups WHERE id = ?";
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
    public List<StudyGroup> getAll() {
        List<StudyGroup> studyGroups;
        final String query = "SELECT * FROM study_groups";
        try (PreparedStatement pStmt =
                     ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = pStmt.executeQuery();
            studyGroups = getStudyGroupsListByResultSet(resultSet);
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return studyGroups;
    }
    private List<StudyGroup> getStudyGroupsListByResultSet(ResultSet resultSet) throws
            SQLException {
        List<StudyGroup> studyGroups = new ArrayList<>();
        while (resultSet.next()) {
            StudyGroup studyGroup = new StudyGroup();
            studyGroup.setId(resultSet.getLong("id"));
            studyGroup.setName(resultSet.getString("group_name"));
            studyGroup.setDescription(resultSet.getString("description"));
            studyGroup.setSubjectArea(resultSet.getString("subject_area"));
            studyGroup.setMeetingSchedule(resultSet.getString("meeting_schedule"));
            studyGroups.add(studyGroup);
        }
        return studyGroups;
    }
    public int getCount() {
        int count = 0;
        try (Statement stmt = ConnectionManager.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM study_groups");
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
            stmt.executeUpdate("DELETE FROM study_groups");
            stmt.executeUpdate("ALTER SEQUENCE study_groups_id_seq RESTART WITH 1");
        } catch (SQLException e) {
            logger.severe("Ошибка: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
