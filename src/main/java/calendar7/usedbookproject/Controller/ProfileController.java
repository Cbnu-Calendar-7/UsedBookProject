package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DAO.SalePostRepository;
import calendar7.usedbookproject.DataBase.DTO.SalePost;
import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController
{
    private User user;
    private SalePostRepository postRepo;

    @Autowired
    public ProfileController(SalePostRepository salePostRepository)
    {
        postRepo = salePostRepository;

        user = new User("TestID", "1234", "test@email.com", "01012345678", "TestNick", "TestName");
        String list1 = "149,150";
        String list2 = "149,150";
        user.setSaleslist(list1);
        user.setWishlist(list2);
    }

    @GetMapping(path = "mylist")
    public String MyList(Model model)
    {
        // user 정보에 있는 리스트를 parsing하여 얻은 postid를 통해
        // SalePost 정보를 찾아서 그 리스트를 전달.
        List<SalePost> list = new ArrayList<>();
        String[] strlist = user.getSaleslist().split(",");

        for(String postid : strlist)
        {
            Optional<SalePost> item = postRepo.findById(Long.parseLong(postid));
            if(item.isPresent()) list.add(item.get());
        }

        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "profile/mylist";
    }

    @GetMapping(path = "archive")
    public String Archive(Model model)
    {
        // user 정보에 있는 리스트를 parsing하여 얻은 postid를 통해
        // SalePost 정보를 찾아서 그 리스트를 전달.
        List<SalePost> list = new ArrayList<>();
        String[] strlist = user.getWishlist().split(",");

        for(String postid : strlist)
        {
            Optional<SalePost> item = postRepo.findById(Long.parseLong(postid));
            if(item.isPresent()) list.add(item.get());
        }

        model.addAttribute("user", user);
        model.addAttribute("list", list);

        return "profile/archive";
    }

}
