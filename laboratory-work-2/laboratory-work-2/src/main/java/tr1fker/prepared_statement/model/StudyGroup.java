package tr1fker.prepared_statement.model;

import lombok.Data;

@Data
public class StudyGroup {
    private Long id;
    private String name;
    private String description;
    private String subjectArea;
    private String meetingSchedule;

    public StudyGroup() {}
    public StudyGroup(String name, String description, String subjectArea, String meetingSchedule) {
        this.name = name;
        this.description = description;
        this.subjectArea = subjectArea;
        this.meetingSchedule = meetingSchedule;
    }
}
