package calendar7.usedbookproject;

import calendar7.usedbookproject.DataBase.DAO.SalePostRepository;
import calendar7.usedbookproject.DataBase.DTO.SalePost;
import calendar7.usedbookproject.FileUpload.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@Controller
public class PostingController
{

    private final StorageService storageService;
    private final SalePostRepository salePostRepository;

    @Autowired
    public PostingController(StorageService storageService, SalePostRepository salePostRepository)
    {
        this.storageService = storageService;
        this.salePostRepository = salePostRepository;
    }

    @PostMapping(path = "/add")
    public String Posting(@RequestParam("aksfileupload[]") MultipartFile file, @RequestParam("title") String title, @RequestParam("deal_method") String Deal_Method,
                          @RequestParam("detail") String Detail, @RequestParam("kakaotalk_url") String Kakao_url, @RequestParam("author") String author,
                          @RequestParam("publisher") String Publisher, @RequestParam("publication_date") String publication_date, @RequestParam("origin_price") String Origin_price,
                          @RequestParam("sale_price") String Sale_price, @RequestParam("Negotiable") Optional<String> Negotiable, @RequestParam("Underline") String Underline,
                          @RequestParam("Note") String Note, @RequestParam("Damage") String Damage, @RequestParam("Cover") String Cover, @RequestParam("deal_place") Optional<String> deal_place)
    {

        SalePost newpost = new SalePost("TestID", "01012345678", "TestNick");

        newpost.setTitle(title);
        newpost.setDetail(Detail);
        newpost.setPublication_date(publication_date);
        newpost.setAuthor(author);
        newpost.setOrigin_Price(Integer.parseInt(Origin_price));
        newpost.setSale_Price(Integer.parseInt(Sale_price));
        newpost.setPublisher(Publisher);
        newpost.setKakaoTalk_url(Kakao_url);


        if(Objects.equals(Deal_Method, "Direct"))
        {
            newpost.setDeal_Method(0);
            newpost.setDeal_place(deal_place.get());
        }
        else if(Objects.equals(Deal_Method, "Parcel")) newpost.setDeal_Method(1);

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
        newpost.setImage_link("image?fileName=" + imagename);

        salePostRepository.save(newpost);

        return "redirect:/list";
    }
}
