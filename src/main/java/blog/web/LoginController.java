package main.java.blog.web;

import main.java.blog.SubscribeEmail;
import main.java.blog.User;
import main.java.blog.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login",method = GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/register",method = GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/signUp",method = POST)
    public String signUp(User user,@RequestPart("picture") MultipartFile picture) throws IOException{
        String originalFilenamename=picture.getOriginalFilename();
        SimpleDateFormat ft2=new SimpleDateFormat("yyyy_MM_dd");
        String pictureName=ft2.format(new Date())+"_"+
                originalFilenamename.substring(originalFilenamename.lastIndexOf("\\")+1);
        String pathname="D:\\myBlog\\out\\artifacts\\MyBlog_war_exploded\\resources\\picture\\"+pictureName;
        //设置url
        user.setAvatar("/resources/picture/"+pictureName);
        //保存到物理路径
        picture.transferTo(new File(pathname));
        userRepository.save(user);
        return "login";
    }


}
