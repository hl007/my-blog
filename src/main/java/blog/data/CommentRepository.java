package main.java.blog.data;

import main.java.blog.*;

import java.util.List;

public interface CommentRepository {

    //保存contact
    void save(Contact contact);

    //保存comment
    void save(Comment comment);

    //保存reply
    void save(Reply reply);

    //根据blog id返回comment
    List<Comment> findByBlogId(String id);

    //返回最近comment
    Comment findRecentComment();

    //返回最近Reply
    Reply findRecentReply();

    //根据comment id返回reply
    List<Reply> findByCommentId(String c_id);

    //根据username返回user
    User findByUsername(String username);

    //订阅邮件
    void save(SubscribeEmail subscribeEmail);

    //返回订阅邮件
    String findEmails();

}
