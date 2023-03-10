package Server;

import Model.Account;
import Model.Student;
import Model.StudentCourse;
import Service.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static ObjectOutputStream objectOutputStream;
    private static Scanner scanner;
    private static ServerSocket serverSocket;
    private static int port = 8888;
    private static IAccountService accountService;
    private static IStudentCourseService studentCourseService;
    private static ICourseService courseService;
    private static IStudentService studentService;

    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        scanner = new Scanner(System.in);
        accountService = new AccountService();
        studentCourseService = new StudentCourseService();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    public static Account authentication() throws IOException, SQLException {
        outputStream.writeUTF("-=-=-=-=-=-Authentication-=-=-=-=-=-");
        outputStream.writeUTF("Username: ");
        String username = inputStream.readUTF();
        outputStream.writeUTF("Password: ");
        String password = inputStream.readUTF();
        Account account = accountService.checkUserCredentials(username, password);
        objectOutputStream.writeObject(account);
        return account;
    }

    public static void mainMenu(Account account) throws IOException, SQLException {
        Student student = studentService.getStudent(account.getStudent_id());
        outputStream.writeUTF("E-learning dashboard\n");
        outputStream.writeUTF("Student information:-\n");
        outputStream.writeUTF("First name:" + student.getFirstName() +"\t\t");
        outputStream.writeUTF("Last name:" + student.getLastName() +"\t\t");
        outputStream.writeUTF("Username:" + account.getUsername() +"\t\t");
        List<StudentCourse> studentCourses = studentCourseService.getStudentCourse(student.getStudentId());
        objectOutputStream.writeObject(studentCourses);
        for (int x = 0; x < studentCourses.size(); x++) {
            outputStream.writeUTF("no.\t\t\tCourse Name\t\t\tCourse ID");
            outputStream.writeUTF((x + 1) + "\t\t\t" + courseService.getCourse(studentCourses.get(x).getCourseId()).getName()
                    + "\t\t\t\t\t" + studentCourses.get(x).getCourseId());
        }

        while (true) {
            try {
                outputStream.writeUTF("Enter a course id:");
                int id = inputStream.readInt();

                HashMap<String, Double> map = studentCourseService.getCourseStatics(id);
                map.put("MED", studentCourseService.getMedian(id));
                map.put("SCORE", studentCourseService.getStudentMark(student.getStudentId(), id));
                outputStream.writeUTF("your mark : " + map.get("SCORE") + "" +
                        "\n average :" + map.get("AVG") + "" +
                        "\n highest :" + map.get("MAX") + "" +
                        "\n lowest  :" + map.get("MIN") + "" +
                        "\n median: :" + map.get("MED"));
                break;
            } catch (Exception exception) {
                outputStream.writeUTF("the course id does not exist");
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        Server server = new Server();
        Account account;
        do {
            account = authentication();
            if(account != null)
            {
                break;
            }
        }while (true);

        do {
            mainMenu(account);
        }while (true);

    }
}