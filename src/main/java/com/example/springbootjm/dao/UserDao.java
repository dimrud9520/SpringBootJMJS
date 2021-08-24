package com.example.springbootjm.dao;


import com.example.springbootjm.models.User;


import java.util.List;

public interface UserDao {

    User getUserByName(String username);

    //добавление юзера
    void addUser(User user);

    //обновление юзера
    void updateUser(User user);

    //получение юзера по id
    User get(long id);

    //удаление юзера по id
    void removeUserById(long id);

    //вывод списка юзеров
    List<User> getAllUsers();

}
