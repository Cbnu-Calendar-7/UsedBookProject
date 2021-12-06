package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DAO.UserRepository;
import calendar7.usedbookproject.DataBase.DTO.User;
import calendar7.usedbookproject.Service.FileUpload.StorageService;
import calendar7.usedbookproject.Service.Login.LoginForm;
import calendar7.usedbookproject.Service.Login.LoginService;
import calendar7.usedbookproject.Service.Login.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final StorageService storageService;
    private final LoginService loginService;

    @Autowired
    public LoginController(UserRepository userRepository, StorageService storageService, LoginService loginService) {
        this.userRepository = userRepository;
        this.storageService = storageService;
        this.loginService = loginService;
    }

    // (기본페이지) 로그인 페이지로 이동
    @GetMapping(path = "/")
    public String Login(@ModelAttribute LoginForm loginForm) {
        return "/login/loginform";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request)
    {

        if (bindingResult.hasErrors())
        {
            bindingResult.reject("loginFail", "ID or PW should not be empty.");
            return "/login/loginform";
        }

        User loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "ID or PW is not correct.");
            return "/login/loginform";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);   // 세션에 로그인 회원 정보 보관

        return "redirect:/list" ;
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
        }

        return "redirect:/";
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
