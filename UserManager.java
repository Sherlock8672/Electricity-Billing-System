
package electricitybillingsystem;

import java.util.*;
import java.io.*;

class UserManager {
    private List<User> users = new ArrayList<>();

    private static final String FILE_NAME = "bills.dat";

    public UserManager() {
        loadFromFile(); 
    }

    public void registerUser(String username, String password, String question) {
        if (findUser(username) != null) {
            System.out.println("Username already exists.");
            return;
        }
        users.add(new User(username, password, question));
        saveToFile();
        System.out.println("User registered. Awaiting admin approval.");
    }

    public User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
}
