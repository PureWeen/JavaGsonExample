// User.java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

// UserManager.java
public class UserManager {
    private static final String FILE_PATH = "users.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeUsersToFile(List<User> users) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> readUsersFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            User[] usersArray = gson.fromJson(reader, User[].class);
            return Arrays.asList(usersArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Create a list of users
        List<User> users = Arrays.asList(
            new User("user1", "password1"),
            new User("user2", "password2")
        );

        // Write users to JSON file
        writeUsersToFile(users);

        // Read users from JSON file
        List<User> readUsers = readUsersFromFile();

        // Print the read users details
        if (readUsers != null) {
            for (User user : readUsers) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
            }
        }
    }
}