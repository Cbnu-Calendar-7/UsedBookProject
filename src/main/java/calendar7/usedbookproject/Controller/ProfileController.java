package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController
{

    @GetMapping(path = "mylist")
    public String MyList(Model model)
    {
        User user = new User("TestID", "1234", "test@email.com", "01012345678", "TestNick", "TestName");

        String list = "1,2,3,4,5,6";
        user.setSaleslist(list);
        user.setWishlist(list);

        model.addAttribute("user", user);
        return "profile/mylist";
    }

    @GetMapping(path = "archive")
    public String Archive()
    {
        return "profile/archive";
    }

}
