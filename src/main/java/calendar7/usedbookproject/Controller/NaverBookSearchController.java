package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.Service.APIBookSearch.Book;
import calendar7.usedbookproject.Service.APIBookSearch.NaverAPIBookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NaverBookSearchController
{
    public NaverAPIBookSearchService naverAPIBookSearchService;

    @Autowired
    public NaverBookSearchController(NaverAPIBookSearchService naverAPIBookSearchService)
    {
        this.naverAPIBookSearchService = naverAPIBookSearchService;
    }


    @GetMapping(path = "/apibooksearch")
    public String apiBookSearch(Model model, @RequestParam("keyword") @Nullable Optional<String> keyword)
    {
        List<Book> list = new ArrayList<>();

        if(keyword.isPresent())
        {
            list = naverAPIBookSearchService.search(keyword.get());
        }

        model.addAttribute("items", list);
        return "main/apiBookSearch";
    }
}
