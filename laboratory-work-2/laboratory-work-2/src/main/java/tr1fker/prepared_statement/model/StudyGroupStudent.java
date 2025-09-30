package tr1fker.prepared_statement.model;

import lombok.Data;

@Data
public class StudyGroupStudent {
    private Long id;
    private Long groupId;
    private Long studentId;

    public StudyGroupStudent() {}
    public StudyGroupStudent(Long groupId, Long studentId) {
        this.groupId = groupId;
        this.studentId = studentId;
    }
}
