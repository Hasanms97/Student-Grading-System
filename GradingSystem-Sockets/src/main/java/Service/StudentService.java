package Service;



import Model.Student;
import Repository.IStudentDAO;
import Repository.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentService implements IStudentService {

    IStudentDAO studentDAO = new StudentDAO();

    @Override
    public void insertStudent(Student student) throws SQLException {
        studentDAO.insertStudent(student);
    }

    @Override
    public void deleteStudent(int id) throws SQLException {
        studentDAO.deleteStudent(id);
    }

    @Override
    public Student getStudent(int id) throws SQLException {
        return studentDAO.getStudent(id);
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}
