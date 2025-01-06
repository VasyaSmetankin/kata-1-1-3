package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Doe", (byte) 25);
        System.out.println("User с именем John добавлен в базу данных");
        userService.saveUser("Jane", "Doe", (byte) 30);
        System.out.println("User с именем Jane добавлен в базу данных");
        userService.saveUser("Alice", "Doe", (byte) 22);
        System.out.println("User с именем Alice добавлен в базу данных");
        userService.saveUser("Bob", "Doe", (byte) 35);
        System.out.println("User с именем Bob добавлен в базу данных");
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
