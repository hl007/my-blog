package main.java.blog.data;

import main.java.blog.*;
import main.java.blog.config.EmailSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
public class JdbcCommentRepostory implements CommentRepository {

    @Autowired
    private JdbcOperations jdbc;

    @Override
    public void save(Contact contact){
        jdbc.update(
                "insert into contact (username,email,subject,message)" +
                        " values (?, ?, ?, ?)",
                contact.getUsername(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getMessage());
    }


    @Override
    public void save(Comment comment){
        jdbc.update(
                "insert into comment (id,c_username,c_avatar,c_time,c_message)" +
                        " values (?, ?, ?, ?, ?)",
                comment.getId(),
                comment.getC_username(),
                comment.getC_avatar(),
                comment.getC_time(),
                comment.getC_message());
    }

    @Override
    public void save(Reply reply){
        jdbc.update(
                "insert into reply (c_id,r_username,r_avatar,r_time,r_message)" +
                        " values (?, ?, ?, ?, ?)",
                reply.getC_id(),
                reply.getR_username(),
                reply.getR_avatar(),
                reply.getR_time(),
                reply.getR_message());
    }

    @Override
    public List<Comment> findByBlogId(String id){
        return jdbc.query(
                "select id,c_id,c_username,c_avatar,c_time,c_message from comment where id=? order by c_id",
                new RowMapper<Comment>() {
                    @Override
                    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Comment(
                                findByCommentId(resultSet.getString("c_id")),
                                resultSet.getString("id"),
                                resultSet.getString("c_id"),
                                resultSet.getString("c_username"),
                                resultSet.getString("c_avatar"),
                                resultSet.getString("c_time"),
                                resultSet.getString("c_message")
                        );
                    }
                },id);
    }

    @Override
    public Comment findRecentComment(){
        return jdbc.query(
                "select id,c_id,c_username,c_avatar,c_time,c_message from comment order by c_id desc",
                new RowMapper<Comment>() {
                    @Override
                    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Comment(
                                findByCommentId(resultSet.getString("c_id")),
                                resultSet.getString("id"),
                                resultSet.getString("c_id"),
                                resultSet.getString("c_username"),
                                resultSet.getString("c_avatar"),
                                resultSet.getString("c_time"),
                                resultSet.getString("c_message")
                        );
                    }
                }).get(0);
    }

    @Override
    public Reply findRecentReply(){
        return jdbc.query(
                "select c_id,r_id,r_username,r_avatar,r_time,r_message from reply order by r_id desc",
                new RowMapper<Reply>() {
                    @Override
                    public Reply mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Reply(
                                resultSet.getString("c_id"),
                                resultSet.getString("r_id"),
                                resultSet.getString("r_username"),
                                resultSet.getString("r_avatar"),
                                resultSet.getString("r_time"),
                                resultSet.getString("r_message")
                        );
                    }
                }).get(0);
    }


    @Override
    public List<Reply> findByCommentId(String c_id){
        return jdbc.query(
                "select c_id,r_id,r_username,r_avatar,r_time,r_message from reply where c_id=? order by r_id",
                new RowMapper<Reply>() {
                    @Override
                    public Reply mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new Reply(
                                resultSet.getString("c_id"),
                                resultSet.getString("r_id"),
                                resultSet.getString("r_username"),
                                resultSet.getString("r_avatar"),
                                resultSet.getString("r_time"),
                                resultSet.getString("r_message")
                        );
                    }
                },c_id);
    }

    @Override
    public User findByUsername(String username){
        List<User> userList=jdbc.query(
                "select username,avatar,id from user where username=?",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new User(
                                resultSet.getString("username"),
                                resultSet.getString("avatar"),
                                resultSet.getString("id")
                        );
                    }
                },username);
        return userList.get(0);
    }

    @Override
    public void save(SubscribeEmail subscribeEmail){
        jdbc.update(
                "insert into emailSubscribe (email,time)" +
                        " values (?, ?)",
                subscribeEmail.getEmail(),
                subscribeEmail.getTime());
    }

    @Override
    public String findEmails(){
        List<SubscribeEmail>  emailList=jdbc.query(
                "select email from emailSubscribe",
                new RowMapper<SubscribeEmail>() {
                    @Override
                    public SubscribeEmail mapRow(ResultSet resultSet, int i) throws SQLException {
                        return new SubscribeEmail(
                                resultSet.getString("email")
                        );
                    }
                });

        Iterator<SubscribeEmail> iterator=emailList.iterator();
        String eList="";
        while(iterator.hasNext()){
            if(eList.equals("")) {
                eList = iterator.next().getEmail();
            }
            else {
                eList=eList+","+iterator.next().getEmail();

            }
        }

        return eList;
    }
}


