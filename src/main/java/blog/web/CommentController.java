package main.java.blog.web;

import main.java.blog.Comment;
import main.java.blog.Contact;
import main.java.blog.Reply;
import main.java.blog.SubscribeEmail;
import main.java.blog.data.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/comment/{id}",method = POST)
    public  String comment(@PathVariable("id") String id, Comment comment, Model model) throws IOException {
        comment.setC_message(new String(comment.getC_message().getBytes("iso-8859-1"),"utf-8"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        comment.setC_username(userDetails.getUsername());
        comment.setId(id);

        SimpleDateFormat ft=new SimpleDateFormat("MM/dd yyyy HH:mm");
        comment.setC_time(ft.format(new Date()));

        comment.setC_avatar(commentRepository.findByUsername(comment.getC_username()).getAvatar());

        synchronized (this) {
            commentRepository.save(comment);
            //赋予c_id值，再取出
            Comment comment2 = commentRepository.findRecentComment();
            model.addAttribute("comment",comment2);
        }
        return "subComment";
    }

    @RequestMapping(value = "/reply/{c_id}",method = POST)
    public String reply(@PathVariable("c_id") String c_id, Reply reply, Model model) throws IOException{
        reply.setR_message(new String(reply.getR_message().getBytes("iso-8859-1"),"utf-8"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        reply.setR_username(userDetails.getUsername());
        reply.setC_id(c_id);

        SimpleDateFormat ft=new SimpleDateFormat("MM/dd yyyy HH:mm");
        reply.setR_time(ft.format(new Date()));
        reply.setR_avatar(commentRepository.findByUsername(reply.getR_username()).getAvatar());

        synchronized (this) {
            commentRepository.save(reply);
            Reply reply2 = commentRepository.findRecentReply();
            model.addAttribute("reply",reply2);
        }

        return "subReply";

    }

    @RequestMapping(value = "/contact/submit",method = POST)
    public String submitContact(Contact contact){
        commentRepository.save(contact);
        return "contact";
    }

    @RequestMapping(value = "/loginSucess",method = GET)
    public String loginSucess(){
        return "loginSucess";
    }

    @RequestMapping(value = "/subscribe",method = POST)
    public @ResponseBody SubscribeEmail subscribe(SubscribeEmail subscribeEmail){
        subscribeEmail.setTime(new Date().toString());
        commentRepository.save(subscribeEmail);
        return subscribeEmail;
    }
}
