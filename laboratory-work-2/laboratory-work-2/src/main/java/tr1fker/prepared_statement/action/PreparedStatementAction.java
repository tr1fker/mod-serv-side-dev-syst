package tr1fker.prepared_statement.action;

import tr1fker.prepared_statement.dao.StudentDao;
import tr1fker.prepared_statement.dao.StudyGroupDao;
import tr1fker.prepared_statement.dao.StudyGroupStudentDao;
import tr1fker.prepared_statement.model.Student;
import tr1fker.prepared_statement.model.StudyGroup;
import tr1fker.prepared_statement.model.StudyGroupStudent;
import tr1fker.statement.model.Author;
import tr1fker.statement.model.Book;
import tr1fker.statement.model.Shop;
import tr1fker.statement.model.ShopBook;
import tr1fker.utils.InputManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PreparedStatementAction {
    private static final Logger logger =
            Logger.getLogger(PreparedStatementAction.class.getName());
    StudentDao studentDao = new StudentDao();
    StudyGroupDao studyGroupDao = new StudyGroupDao();
    StudyGroupStudentDao studyGroupStudentDao = new StudyGroupStudentDao();
    public void insertStudent() {
        Student student = Student.getStudentFromInput();
        studentDao.insert(student); // Если произойдёт исключение, отработает блок catch из PreparedStatementMenu
        System.out.println("Студент добавлен успешно!");
    }
    public void updateStudent() {
        System.out.print("Введи id студента: ");
        Student student = studentDao.getById(InputManager.getNextLong());
        if (student == null) {
            System.out.println("Студент с введённым id не найден!");
        } else {
            Student newStudent = Student.getStudentFromInput();
            newStudent.setId(student.getId());
            studentDao.update(newStudent);
            System.out.println("Студент обновлён успешно!");
        }
    }
    public void deleteStudent() {
        System.out.print("Введи id студента: ");
        Student student = studentDao.getById(InputManager.getNextLong());
        if (student == null) {
            System.out.println("Студент с введённым id не найден!");
        } else {
            studentDao.delete(student.getId());
            System.out.println("Студент удалён успешно!");
        }
    }
    public void getStudentById() {
        System.out.print("Введи id студента: ");
        Student student = studentDao.getById(InputManager.getNextLong());
        if (student == null) {
            System.out.println("Студент с введённым id не найден!");
        } else {
            System.out.println("[Студент по id]\n" + student);
        }
    }
    public void getStudentsByFirstName() {
        System.out.print("Введи имя (или его часть): ");
        List<Student> students = studentDao.getByFirstName(InputManager.getNextLine());
        if (students.isEmpty()) {
            System.out.println("Не найдено ни одного студента с похожим именем!");
            return;
        }
        System.out.println("[Студенты по имени]");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void getStudentsByLastName() {
        System.out.print("Введи фамилию (или её часть): ");
        List<Student> students = studentDao.getByLastName(InputManager.getNextLine());
        if (students.isEmpty()) {
            System.out.println("Не найдено ни одного студента с похожей фамилией!");
            return;
        }
        System.out.println("[Студенты по фамилии]");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void getStudentsByCity() {
        System.out.print("Введи название города (или его часть): ");
        List<Student> students = studentDao.getByCity(InputManager.getNextLine());
        if (students.isEmpty()) {
            System.out.println("Не найдено ни одного студента из города с похожим наименованием!");
            return;
        }
        System.out.println("[Студенты по городу]");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void getStudentsByEmail() {
        System.out.print("Введи электронную почту (или её часть): ");
        List<Student> students = studentDao.getByEmail(InputManager.getNextLine());
        if (students.isEmpty()) {
            System.out.println("Не найдено ни одного студента с похожей электронной почтой!");
            return;
        }
        System.out.println("[Студенты по электронной почте]");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void getStudentsByAge() {
        int start;
        int end;
        while (true) {
            System.out.print("Введи начальный возраст для поиска: ");
            start = InputManager.getNextIntInRange(17, 71);
            System.out.print("Введи конечный возраст для поиска: ");
            end = InputManager.getNextIntInRange(17, 71);
            if (start > end) {
                System.out.println("Начальный возраст не может быть > конечного!");
            } else {
                break;
            }
        }
        List<Student> students = studentDao.getByAge(start, end);
        if (students.isEmpty()) {
            System.out.println("Не найдено ни одного студента с похожей электронной почтой!");
            return;
        }
        System.out.println("[Студенты по возрасту]");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    public void getAllStudents () {
        List<Student> students = studentDao.getAll();
        if (students.isEmpty()) {
            System.out.println("Нет ни одного студента!");
            return;
        }
        System.out.println("[Все студенты]");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void addDefaultInfStudyGroupsSs(){
        try {
            if (studyGroupDao.getCount() != 0 || studyGroupStudentDao.getCount() != 0) {
                System.out.println("Таблицы не пусты. Вставка информации по умолчанию невозможна!");
                return;
            }
            List<Student> students = getDefaultStudents();
            for (Student student : students){
                studentDao.insert(student);
            }
            List<StudyGroup> studyGroups = getDefaultStudyGroups();
            for (StudyGroup studyGroup : studyGroups) {
                studyGroupDao.insert(studyGroup);
            }
            List<StudyGroupStudent> studyGroupStudents = getDefaultStudyGroupStudents();
            for (StudyGroupStudent studyGroupStudent : studyGroupStudents) {
                studyGroupStudentDao.insert(studyGroupStudent);
            }
            System.out.println("Информация по умолчанию вставлена успешно!");
        } catch (RuntimeException e) {
            logger.severe("При добавлении информации в таблицы возникла ошибка: " +
                    e.getMessage());
        }
    }

    private List<StudyGroup> getDefaultStudyGroups(){
        List<StudyGroup> studyGroups = new ArrayList<>();
        studyGroups.add(new StudyGroup("Java Beginners", "Группа для начинающих изучать Java",
                "Программирование", "Понедельник/Среда 18:00-20:00"));
        studyGroups.add(new StudyGroup("Math Pro", "Продвинутая математика для студентов",
                "Математика", "Вторник/Пятница 17:00-19:00"));
        studyGroups.add(new StudyGroup("English Club", "Разговорный английский язык",
                "Английский язык", "Каждый день 19:00-20:00"));
        studyGroups.add(new StudyGroup("Book Lovers", "Литературный кружок",
                "Литература", "Суббота 15:00-17:00"));
        studyGroups.add(new StudyGroup("Science Team", "Научные исследования и проекты",
                "Естественные науки", "Четверг 16:00-18:00"));
        return studyGroups;
    }

    private List<StudyGroupStudent> getDefaultStudyGroupStudents(){
        List<StudyGroupStudent> studyGroupStudents = new ArrayList<>();
        studyGroupStudents.add(new StudyGroupStudent(1L, 1L));
        studyGroupStudents.add(new StudyGroupStudent(1L, 2L));
        studyGroupStudents.add(new StudyGroupStudent(1L, 3L));
        studyGroupStudents.add(new StudyGroupStudent(1L, 4L));
        studyGroupStudents.add(new StudyGroupStudent(2L, 3L));
        studyGroupStudents.add(new StudyGroupStudent(2L, 5L));
        studyGroupStudents.add(new StudyGroupStudent(2L, 6L));
        studyGroupStudents.add(new StudyGroupStudent(3L, 1L));
        studyGroupStudents.add(new StudyGroupStudent(3L, 4L));
        studyGroupStudents.add(new StudyGroupStudent(3L, 7L));
        studyGroupStudents.add(new StudyGroupStudent(3L, 8L));
        studyGroupStudents.add(new StudyGroupStudent(3L, 9L));
        studyGroupStudents.add(new StudyGroupStudent(4L, 2L));
        studyGroupStudents.add(new StudyGroupStudent(4L, 6L));
        studyGroupStudents.add(new StudyGroupStudent(4L, 10L));
        studyGroupStudents.add(new StudyGroupStudent(5L, 5L));
        studyGroupStudents.add(new StudyGroupStudent(5L, 7L));
        studyGroupStudents.add(new StudyGroupStudent(5L, 8L));
        studyGroupStudents.add(new StudyGroupStudent(5L, 9L));
        return studyGroupStudents;
    }

    private List<Student> getDefaultStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван", "Петров", 20, "Москва",
                "ivan.petrov@mail.ru"));
        students.add(new Student("Мария", "Сидорова", 21, "Санкт-Петербург",
                "maria.sidorova@gmail.com"));
        students.add(new Student("Алексей", "Козлов", 19, "Новосибирск",
                "alex.kozlov@yandex.ru"));
        students.add(new Student("Елена", "Николаева", 22, "Екатеринбург",
                "elena.nikolaeva@mail.ru"));
        students.add(new Student("Дмитрий", "Васильев", 20, "Казань",
                "dmitry.vasiliev@gmail.com"));
        students.add(new Student("Ольга", "Морозова", 21, "Москва",
                "olga.morozova@yandex.ru"));
        students.add(new Student("Сергей", "Федоров", 23, "Санкт-Петербург",
                "sergey.fedorov@mail.ru"));
        students.add(new Student("Анна", "Павлова", 20, "Новосибирск",
                "anna.pavlova@gmail.com"));
        students.add(new Student("Павел", "Семенов", 22, "Екатеринбург",
                "pavel.semenov@yandex.ru"));
        students.add(new Student("Наталья", "Волкова", 21, "Казань",
                "natalia.volkova@mail.ru"));
        return students;
    }

    public void deleteAllInfo() {
        System.out.print("Подтвердите удаление всей информации (y / Y): ");
        final String choice = InputManager.getNextLine();
        if (!choice.equals("Y") && !choice.equals("y")) {
            System.out.println("Удаление было отменено!");
            return;
        }
        try {
            studyGroupStudentDao.clearTable();
            studentDao.clearTable();
            studyGroupDao.clearTable();
            System.out.println("Информация из таблиц была успешно удалена!");
        } catch (RuntimeException e) {
            logger.severe("При удалении информации из таблиц ошибка: " + e.getMessage());
        }
    }

    public void getAllInfo() {
        try {
            List<Student> students = studentDao.getAll();
            if (students.isEmpty()) {
                System.out.println("Таблица студентов пуста!");
            } else {
                System.out.println("[Студенты]");
                for (Student student : students) {
                    System.out.println(student);
                }
            }
            List<StudyGroup> studyGroups = studyGroupDao.getAll();
            if (studyGroups.isEmpty()) {
                System.out.println("Таблица учебных групп пуста!");
            }else{
                System.out.println("[Учебные группы]");
                for (StudyGroup studyGroup : studyGroups) {
                    System.out.println(studyGroup);
                }
            }
            List<StudyGroupStudent> studyGroupStudents = studyGroupStudentDao.getAll();
            if (studyGroupStudents.isEmpty()) {
                System.out.println("Таблица студентов в группах пуста!");
            }else{
                System.out.println("[Студенты в группах]");
                for (StudyGroupStudent studyGroupStudent : studyGroupStudents) {
                    System.out.println(studyGroupStudent);
                }
            }
        } catch (RuntimeException e) {
            logger.severe("При просмотре информации возникла ошибка: " + e.getMessage());
        }
    }
}