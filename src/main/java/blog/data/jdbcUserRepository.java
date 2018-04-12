package main.java.blog.data;
;
import main.java.blog.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class jdbcUserRepository implements UserRepository {

    @Autowired
    JdbcOperations jdbc;

    @Override
    public void save(User user){
        jdbc.update("insert into user (email,username,password,avatar)" +
                " values (?, ?, ?,?)",user.getEmail(),user.getUsername(),user.getPassword(),user.getAvatar());
    }

    @Override
    public User findByName(String name){
        List<User> userList=jdbc.query("select username,password from user where username=?",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new User(
                                resultSet.getString("username"),
                                resultSet.getString("password"));
                    }
                },name);

        return userList.get(0);
    }
}
