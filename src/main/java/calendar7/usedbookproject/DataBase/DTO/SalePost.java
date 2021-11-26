package calendar7.usedbookproject.DataBase.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "SalePost")
@Getter
@Setter
public class SalePost
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postID;
    private String phoneNumber;
    private String userID;
    private String nickName;

    private String title; // 책 제목
    private Date postDay; // 등록일
    private Integer originPrice; // 원래 가격
    private Integer salePrice; // 판매 가격
    private String author; // 지은이
    private String publisher; // 출판사
    private Boolean negotiable; // 흥정가능여부
    private String detail; // 설명
    private Integer dealMethod; // 거래 방법, 직거래:0 택배:1
    private String kakaoTalkUrl; // 카카오톡 오픈채팅방 링크
    private String imageLink; // 이미지 링크, 서버에 저장된 이미지의 절대경로.
    private String dealPlace; // 거래 장소, 거래방법을 직거래로 선택시 입력.
    private String publicationDate; // 출판일

    // 책의 상태
    private Integer note;   // 필기, 없음:0 연필/샤프:1 볼팬/형광팬:2
    private Integer underline; // 밑줄, 없음:0 연필/샤프:1 볼팬/형광팬:2
    private Boolean damage; // 페이지 변색/훼손
    private Integer cover; // 겉표지, 꺠끗함:0 약간 더러움:1 많이 더러움:2


    public SalePost(String UserID, String phonenumber, String nickName)
    {
        phoneNumber = phonenumber;
        this.userID = UserID;
        this.nickName = nickName;
        postDay = new Date();
    }

    public SalePost(String phonenumber, String userID, String nickName,
                     String title, Integer origin_Price,
                    Integer sale_Price, String author, String publisher,
                     Boolean isDeal, String detail, Integer deal_Method,
                    String image_link, Boolean damage, Integer note, Integer underline,
                    Integer cover, String deal_place, String publication_date)
    {
        phoneNumber = phonenumber;
        this.userID = userID;
        this.nickName = nickName;

        postDay = new Date();

        this.title = title;
        originPrice = origin_Price;
        salePrice = sale_Price;
        this.author = author;
        this.publisher = publisher;
        negotiable = isDeal;
        this.detail = detail;
        dealMethod = deal_Method;
        imageLink = image_link;
        this.note = note;
        this.underline = underline;
        this.damage = damage;
        this.cover = cover;
        dealPlace = deal_place;
        publicationDate = publication_date;
    }

    public SalePost() {}

    @Override
    public String toString() {
        return "SalePost{" +
                "Title='" + title + '\'' +
                ", Postday=" + postDay +
                ", Origin_Price=" + originPrice +
                ", Sale_Price=" + salePrice +
                ", Author='" + author + '\'' +
                ", Publisher='" + publisher + '\'' +
                ", Negotiable=" + negotiable +
                ", Detail='" + detail + '\'' +
                ", Deal_Method=" + dealMethod +
                ", KakaoTalk_url='" + kakaoTalkUrl + '\'' +
                ", Image_link='" + imageLink + '\'' +
                ", Deal_place='" + dealPlace + '\'' +
                ", Publication_date='" + publicationDate + '\'' +
                ", Note=" + note +
                ", Underline=" + underline +
                ", Damage=" + damage +
                ", Cover=" + cover +
                '}';
    }
}
