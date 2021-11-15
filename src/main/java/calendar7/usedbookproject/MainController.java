package calendar7.usedbookproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController
{

    // (기본페이지) 로그인 페이지로 이동
    @GetMapping(path = "/")
    public String Login () { return "login/signin"; }

    // 계정 생성 페이지로 이동
    @GetMapping(path = "/login/createAccount")
    public String CreateAccount() { return "login/createAccount"; }

    // 계정 찾기 페이지로 이동
    @GetMapping(path = "/login/searchAccount")
    public String SearchAccount() { return "login/searchAccount"; }

    // 판매 목록 리스트 페이지로 이동
    @GetMapping(path = "/main/ProductList")
    public String ProductList() { return "main/ProductList"; }

    // 판매 등록 페이지로 이동
    @GetMapping(path = "/main/sellProduct")
    public String sellProduct() { return "main/sellProduct"; }

    // 계정 생성 페이지로 이동
    @GetMapping(path = "/chat")
    public String Chat() { return "chat"; }

    // 프로필 페이지로 이동
    @GetMapping(path = "/UserProfile")
    public String ToCreateAccoungPage() { return "UserProfile"; }


}
