package calendar7.usedbookproject;

import calendar7.usedbookproject.DataBase.DAO.UserRepository;
import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController
{
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public  @ResponseBody String addNewUser (@RequestParam String UserID, @RequestParam String password, @RequestParam String email,
                                             @RequestParam String phonenumber, @RequestParam String nickname)
    {
        User user = new User(UserID, password, email, phonenumber, nickname);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }

}
