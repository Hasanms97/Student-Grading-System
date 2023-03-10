package Client;

import Model.Account;
import Model.StudentCourse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Client {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static ObjectInputStream objectInputStream;
    private static Scanner scanner;
    private static Socket socket;

    public Client() throws IOException {
        socket = new Socket("localhost", 8888);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        scanner = new Scanner(System.in);
    }

    public static Account authentication() throws IOException, ClassNotFoundException {
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readUTF());
        dataOutputStream.writeUTF(scanner.nextLine());
        System.out.println(dataInputStream.readUTF());
        dataOutputStream.writeUTF(scanner.nextLine());
        Account account = (Account) objectInputStream.readObject();
        return  account;
    }

    public static void mainMenu() throws IOException, ClassNotFoundException {
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readUTF());
        List<StudentCourse>studentCourses = (List<StudentCourse>) objectInputStream.readObject();
        for(int x = 0; x < studentCourses.size() ;x++)
        {
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readUTF());
        }

        while (true) {
            try {
                System.out.println(dataInputStream.readUTF());
                dataOutputStream.writeInt(scanner.nextInt());
                System.out.println(dataInputStream.readUTF());
            } catch (Exception exception) {
                System.out.println(dataInputStream.readUTF());
            }
        }
    }
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Client client = new Client();


        do {
            Account account = authentication();
            if(account != null)
            {
                break;
            }
        }while (true);

        do {
            mainMenu();
        }while (true);

    }
}