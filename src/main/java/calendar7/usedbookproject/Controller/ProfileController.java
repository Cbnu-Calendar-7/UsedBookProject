package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DAO.SalePostRepository;
import calendar7.usedbookproject.DataBase.DTO.SalePost;
import calendar7.usedbookproject.DataBase.DTO.User;
import calendar7.usedbookproject.Service.Login.SessionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController
{
    private SalePostRepository postRepo;

    @Autowired
    public ProfileController(SalePostRepository salePostRepository)
    {
        postRepo = salePostRepository;
    }

    @GetMapping(path = "mylist")
    public String MyList(Model model, HttpServletRequest request)
    {

        // 세션이 없으면 로그인 페이지로 이동
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            return "/login/loginform";
        }

        // 세션에 저장된 회원 조회
        User user = (User) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (user == null) {
            return "/login/loginform";
        }

        // user 정보에 있는 리스트를 parsing하여 얻은 postid를 통해
        // SalePost 정보를 찾아서 그 리스트를 전달.
        List<SalePost> list = new ArrayList<>();

        if(!user.getSaleslist().equals(""))
        {
            String[] strlist = user.getSaleslist().split(",");
            for(String postid : strlist)
            {
                Optional<SalePost> item = postRepo.findById(Long.parseLong(postid));
                if(item.isPresent()) list.add(item.get());
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "profile/mylist";
    }

    @GetMapping(path = "archive")
    public String Archive(Model model, HttpServletRequest request)
    {

        // 세션이 없으면 로그인 페이지로 이동
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            return "/login/loginform";
        }

        // 세션에 저장된 회원 조회
        User user = (User) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (user == null) {
            return "/login/loginform";
        }

        model.addAttribute("user", user);

        // user 정보에 있는 리스트를 parsing하여 얻은 postid를 통해
        // SalePost 정보를 찾아서 그 리스트를 전달.
        List<SalePost> list = new ArrayList<>();

        if(!user.getWishlist().equals(""))
        {
            String[] strlist = user.getWishlist().split(",");
            for(String postid : strlist)
            {
                Optional<SalePost> item = postRepo.findById(Long.parseLong(postid));
                if(item.isPresent()) list.add(item.get());
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("list", list);

        return "profile/archive";
    }

}
