package web.service;



import web.models.User;

import java.util.List;

public interface UserService {
    //добавление юзера
    void addUser(User user);

    //редактирование юзера
    void updateUser(User user);

    //получение юзера по id
    User get(long id);

    //удаление юзера по id
    void removeUserById(long id);

    //вывод списка юзеров
    List<User> getAllUsers();
}
