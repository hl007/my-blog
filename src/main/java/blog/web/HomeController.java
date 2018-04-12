package main.java.blog.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import main.java.blog.Blog;
import main.java.blog.Comment;
import main.java.blog.Reply;
import main.java.blog.config.EmailSubscription;
import main.java.blog.data.BlogRepository;
import main.java.blog.data.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HomeController {

    //实现pre/next
    private int batch=1;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    EmailSubscription emailSubscription;

    @RequestMapping(value = "/home/pre",method = GET)
    public String pre(Model model) {
        batch--;
        if(batch==0){
            batch++;
        }
        List<Blog> blogList=blogRepository.findBlogs(batch, 9);
        List<Blog> titleList=blogRepository.findRecentBlogs();
        Set<String> tagSet=blogRepository.findTags();
        model.addAttribute("titleList",titleList);
        model.addAttribute(blogList);
        model.addAttribute("tagSet",tagSet);
        return "home";
    }

    @RequestMapping(value = "/home/next",method = GET)
    public String next(Model model) {
        batch++;
        int totalBatch=blogRepository.countBlogs()/9+1;
        if(batch==totalBatch+1){
            batch--;
        }
        List<Blog> blogList=blogRepository.findBlogs(batch, 9);
        List<Blog> titleList=blogRepository.findRecentBlogs();
        Set<String> tagSet=blogRepository.findTags();
        model.addAttribute("titleList",titleList);
        model.addAttribute(blogList);
        model.addAttribute("tagSet",tagSet);
        return "home";
    }

     @RequestMapping(value = "/home", method = GET)
     public String home(Model model){
         batch=1;
         List<Blog> blogList= blogRepository.findBlogs(1,9);
         List<Blog> titleList=blogRepository.findRecentBlogs();
         Set<String> tagSet=blogRepository.findTags();
         model.addAttribute("titleList",titleList);
         model.addAttribute(blogList);
         model.addAttribute("tagSet",tagSet);
         return "home";
     }

    @RequestMapping(value = "/tag/{tag}",method= GET)
    public String tag(@PathVariable("tag") String tag,Model model){
        List<Blog> blogList= blogRepository.findByTag(tag);;
        List<Blog> titleList=blogRepository.findRecentBlogs();
        Set<String> tagSet=blogRepository.findTags();
        model.addAttribute(blogList);
        model.addAttribute("titleList",titleList);
        model.addAttribute("tagSet",tagSet);
        return "home";
     }

    @RequestMapping(value = "/about", method = GET)
    public String about(Model model){
        Blog blog=blogRepository.findById("140");
        model.addAttribute(blog);
        return "about";
    }

    @RequestMapping(value = "/contact", method = GET)
    public String contact(){
        return "contact";
    }

    @RequestMapping(value = "/edit", method = GET)
    public String upload(){
        return "edit";
    }

    @RequestMapping(value = "/blog", method = POST)
    public Blog blog(Blog blog, @RequestPart("picture") MultipartFile picture,Model model) throws IOException{
        SimpleDateFormat ft=new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        SimpleDateFormat ft2=new SimpleDateFormat("yyyy_MM_dd");
        String date=ft.format(new Date());
        blog.setDate(date);
        String originalFilenamename=picture.getOriginalFilename();
        String pictureName=ft2.format(new Date())+"_"+
                originalFilenamename.substring(originalFilenamename.lastIndexOf("\\")+1);
        String pathname="D:\\myBlog\\out\\artifacts\\MyBlog_war_exploded\\resources\\picture\\"+pictureName;
        //设置url
        blog.setPathname("/resources/picture/"+pictureName);
        //保存到物理路径
        picture.transferTo(new File(pathname));
        blog.setName("hl007");

        //转码
        blog.setTitle(new String(blog.getTitle().getBytes("iso-8859-1"),"utf-8"));
        blog.setSummary(new String(blog.getSummary().getBytes("iso-8859-1"),"utf-8"));
        blog.setContent(new String(blog.getContent().getBytes("iso-8859-1"),"utf-8"));
        blog.setTag1(new String(blog.getTag1().getBytes("iso-8859-1"),"utf-8"));
        blog.setTag2(new String(blog.getTag2().getBytes("iso-8859-1"),"utf-8"));
        blog.setTag3(new String(blog.getTag3().getBytes("iso-8859-1"),"utf-8"));
        blog.setTag4(new String(blog.getTag4().getBytes("iso-8859-1"),"utf-8"));

        //保存blog
        blogRepository.save(blog);

        List<Blog> titleList=blogRepository.findRecentBlogs();
        Set<String> tagSet=blogRepository.findTags();
        model.addAttribute("titleList",titleList);
        model.addAttribute("tagSet",tagSet);
        model.addAttribute(blog);

        //发送邮件
        if(! commentRepository.findEmails().equals("")) {
            try {
                InternetAddress[] internetAddresses = InternetAddress.parse(commentRepository.findEmails());
                emailSubscription.sendEmail(internetAddresses, blog.getTitle(), blog.getContent());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return blog;
    }

    @RequestMapping(value="/blog/{id}", method = GET)
    public String singleBlog (@PathVariable("id") String id,Model model) {
        Blog blog=blogRepository.findById(id);
        List<Blog> blogList=blogRepository.findRelatedBlogs(blog);
        List<Blog> titleList=blogRepository.findRecentBlogs();


        //获取comment list
        List<Comment> commentList=commentRepository.findByBlogId(id);
        //获取reply list
        List<Reply> replyList=new ArrayList<Reply>();
        //获取comment id
        Iterator iterator=commentList.iterator();
        for(int i=1;iterator.hasNext();i++){
            Comment comment=(Comment)(iterator.next());
            replyList.addAll(commentRepository.findByCommentId(comment.getC_id()));
        }

        Set<String> tagSet=blogRepository.findTags();
        model.addAttribute("titleList",titleList);
        model.addAttribute("tagSet",tagSet);
        model.addAttribute(blog);
        model.addAttribute(blogList);
        model.addAttribute(commentList);
        model.addAttribute(replyList);
        return "blog";
    }
 }

