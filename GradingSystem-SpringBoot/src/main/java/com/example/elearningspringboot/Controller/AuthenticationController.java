package com.example.elearningspringboot.Controller;

import com.example.elearningspringboot.Entity.Account;
import com.example.elearningspringboot.Entity.Student;
import com.example.elearningspringboot.Service.AccountService;
import com.example.elearningspringboot.Service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AccountService accountService;

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public String getSignIn(Model model) {
        model.addAttribute("account", new Account());
        return "signIn";
    }

    @PostMapping("/signin")
    public String signInSubmitHandler(Account account, HttpSession session) {
        Account temp = accountService.checkUserCredentials(account.getUsername(),account.getPassword());
        if(temp != null)
        {
            session.setAttribute("account",temp);
            session.setAttribute("user",studentService.getStudent(temp.getStudent_id()));
            return "redirect:/auth/home";
        }
        return "redirect:/auth/";
    }
    @GetMapping("/signout")
    public String signOutHandler(HttpSession session)
    {
        session.removeAttribute("user");
        session.removeAttribute("account");
        return "redirect:/auth/";
    }
    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("user", new Student());
        return "singUp";
    }
    @PostMapping("/auth_sign-up")
    public String singUpSubmitHandler(Account account,Student student, @RequestParam String confirmPassword, HttpSession session) {
        Account temp = accountService.createUserCredentials(account,confirmPassword);
        Student d = new Student("sdfsd","sdfsdf");
        if(temp == null)
        {
            session.setAttribute("account",temp);
            session.setAttribute("user",studentService.getStudent(accountService.findUsersByUsername(account.getUsername()).getStudent_id()));
            return "redirect:/auth/home";
        }
        return "redirect:/auth/";
    }
    @GetMapping("/home")
    public String getHome(Model model,HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        Student student = (Student) session.getAttribute("user");
        model.addAttribute("account",account);
        model.addAttribute("student",student);
        return "home";
    }
}
