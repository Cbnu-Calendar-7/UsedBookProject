package calendar7.usedbookproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController
{

    @GetMapping(path = "profile/mylist")
    public String MyList()
    {
        return "profile/mylist";
    }

    @GetMapping(path = "profile/archive")
    public String Archive()
    {
        return "profile/archive";
    }

}
