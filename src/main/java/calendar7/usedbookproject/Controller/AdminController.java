package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DTO.User;
import calendar7.usedbookproject.Service.Admin.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final AdminLoginService adminLoginService;

    @Autowired
    public AdminController(AdminLoginService adminLoginService) {
        this.adminLoginService = adminLoginService;
    }

    @GetMapping(path = "/admin")
    public String loginAdmin(Model model) {

        List<User> tempList = adminLoginService.findAllUser();
        model.addAttribute("waitings", tempList);

        return "login/LoginAdmin";
    }
}
