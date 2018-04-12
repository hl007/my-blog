package main.java.blog.data;

import main.java.blog.User;

public interface UserRepository {

    //保存user
    void save(User user);

    //查询user
    User findByName(String name);
}
