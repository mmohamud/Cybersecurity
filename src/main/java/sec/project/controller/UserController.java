package sec.project.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.User;
import sec.project.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        if (userRepository.findAll().isEmpty()) {
        User user = new User("admin", "admin", true);
        userRepository.save(user);
        }
        
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loadIndex() {
        return "index";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        
        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "redirect:/" + user.getId() + "/contacts";
            }
        }
        return "index";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/index";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(HttpSession session, @RequestParam String username, @RequestParam String password) {
        User user = new User(username, password, false);
        userRepository.save(user);
        session.setAttribute("user", user);
        return "redirect:/" + user.getId() + "/contacts";
    }
    
    @RequestMapping(value = "/{id}/changepassword", method = RequestMethod.GET)
    public String changePasswordForm(Model model, @PathVariable Long id){
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        return "changePassword";
    }
    
    @RequestMapping(value = "/{id}/changepassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam String password, @PathVariable Long id) {
        User user = userRepository.getOne(id);
        if (user == null) {
            return "redirect:/{id}/changepassword";
        }
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/" + user.getId() + "/contacts";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }
}
