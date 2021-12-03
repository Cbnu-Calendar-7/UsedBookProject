package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DAO.UserRepository;
import calendar7.usedbookproject.DataBase.DTO.User;
import calendar7.usedbookproject.Service.FileUpload.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final StorageService storageService;

    @Autowired
    public MainController(UserRepository userRepository, StorageService storageService) {
        this.userRepository = userRepository;
        this.storageService = storageService;
    }


    // (기본페이지) 로그인 페이지로 이동
    @GetMapping(path = "/")
    public String Login() {
        return "login/signin";
    }

    // 계정 생성 페이지로 이동
    @GetMapping(path = "/login/createAccount")
    public String CreateAccount() {
        return "login/createAccount";
    }

    // 계정 찾기 페이지로 이동
    @GetMapping(path = "/login/searchAccount")
    public String SearchAccount() {
        return "login/searchAccount";
    }

    // 프로필 페이지로 이동
    @GetMapping(path = "/UserProfile")
    public String ToCreateAccoungPage() {
        return "UserProfile";
    }

    @PostMapping("/PushAccount")
    public String PushAccount(@RequestParam("userid") String userId, @RequestParam("userpassword") String userPassword, @RequestParam("username") String userName,
                              @RequestParam("usernickname") String userNickname, @RequestParam("useremail") String userEmail, @RequestParam("userphonenumber") String userPhonenumber,
                              @RequestParam("imagefile") MultipartFile imgFile) {

        User tempUser = new User(userId, userPassword, userEmail, userPhonenumber, userNickname, userName);

        String imageName = storageService.store(imgFile);
        tempUser.setStudentIdCardImgLink("image?fileName=" + imageName);

        userRepository.save(tempUser);

        return "redirect:/";
    }


}
