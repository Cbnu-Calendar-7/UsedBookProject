package calendar7.usedbookproject.Controller;

import calendar7.usedbookproject.DataBase.DAO.SalePostRepository;
import calendar7.usedbookproject.DataBase.DTO.SalePost;
import calendar7.usedbookproject.DataBase.DTO.User;
import calendar7.usedbookproject.Service.FileUpload.StorageService;
import calendar7.usedbookproject.Service.Login.SessionConstants;
import calendar7.usedbookproject.Service.Search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class    PostingController
{

    private final StorageService storageService;
    private final SearchService searchService;
    private final SalePostRepository postRepo;

    @Autowired
    public PostingController(StorageService storageService, SalePostRepository salePostRepository, SearchService searchService)
    {
        this.storageService = storageService;
        this.postRepo = salePostRepository;
        this.searchService = searchService;
    }

    @GetMapping(path = "/add")
    public String SalePostAdd()
    {
        return "main/Add_Post";
    }

    @GetMapping(path = "/delete")
    public String SalePostDelete(@RequestParam("postid") Long postid, @RequestParam("redirecturl") String redirecturl)
    {
        postRepo.deleteById(postid);
        return "redirect:/" + redirecturl;
    }

    @GetMapping(path = "/list")
    public String list(Model model, @RequestParam(value = "keyword") @Nullable Optional<String> keyword, HttpServletRequest request)
    {

        // 세션이 없으면 로그인 페이지로 이동
        HttpSession session = request.getSession(false);
        if (session == null)
        {
            return "/login/loginform";
        }

        // 세션에 저장된 회원 조회
        User loginMember = (User) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if (loginMember == null) {
            return "/login/loginform";
        }

        model.addAttribute("user", loginMember);

        if(keyword.isPresent())
        {
            // 검색결과, 검색 키워드, 검색 날짜를 넘긴다.
            List<SalePost> list = searchService.SearchByTitle(keyword.get());
            model.addAttribute("items", list);
            model.addAttribute("keyword", keyword);
            model.addAttribute("date", new Date());

            return "main/List_View";
        }
        return "main/Search_Page";
    }

    @PostMapping(path = "/add")
    public String Posting(@RequestParam("aksfileupload[]") MultipartFile file, @RequestParam("title") String title, @RequestParam("deal_method") String Deal_Method,
                          @RequestParam("detail") String Detail, @RequestParam("kakaotalk_url") String Kakao_url, @RequestParam("author") String author,
                          @RequestParam("publisher") String Publisher, @RequestParam("publication_date") String publication_date, @RequestParam("origin_price") String Origin_price,
                          @RequestParam("sale_price") String Sale_price, @RequestParam("Negotiable") Optional<String> Negotiable, @RequestParam("Underline") String Underline,
                          @RequestParam("Note") String Note, @RequestParam("Damage") String Damage, @RequestParam("Cover") String Cover, @RequestParam("deal_place") Optional<String> deal_place,
                          @RequestParam("isbn10") String isbn10, @RequestParam("isbn13") String isbn13, HttpServletRequest request, Model model)
    {

        SalePost newpost = new SalePost("TestID", "01012345678", "TestNick");

        newpost.setTitle(title);
        newpost.setDetail(Detail);
        newpost.setPublicationDate(publication_date);
        newpost.setAuthor(author);
        newpost.setOriginPrice(Origin_price);
        newpost.setSalePrice(Sale_price);
        newpost.setPublisher(Publisher);
        newpost.setKakaoTalkUrl(Kakao_url);


        if(Objects.equals(Deal_Method, "Direct"))
        {
            newpost.setDealMethod(0);
            newpost.setDealPlace(deal_place.get());
        }
        else if(Objects.equals(Deal_Method, "Parcel")) newpost.setDealMethod(1);

        newpost.setNegotiable(Negotiable.isPresent());

        if(Objects.equals(Note, "None")) newpost.setNote(0);
        else if(Objects.equals(Note, "Pencil")) newpost.setNote(1);
        else if(Objects.equals(Note, "Highlighter")) newpost.setNote(2);

        if(Objects.equals(Underline, "None")) newpost.setUnderline(0);
        else if(Objects.equals(Underline, "Pencil")) newpost.setUnderline(1);
        else if(Objects.equals(Underline, "Highlighter")) newpost.setUnderline(2);

        if(Objects.equals(Damage, "true")) newpost.setDamage(true);
        else if(Objects.equals(Damage, "false")) newpost.setDamage(false);

        if(Objects.equals(Cover, "clean")) newpost.setCover(0);
        else if(Objects.equals(Cover, "little_dirty")) newpost.setCover(1);
        else if(Objects.equals(Cover, "very_dirty")) newpost.setCover(2);

        String imagename = storageService.store(file);
        newpost.setImageLink("image?fileName=" + imagename);

        Long postID = postRepo.save(newpost).getPostID();

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

        user.addSaleList(postID.toString());

        return "redirect:/list";
    }
}
