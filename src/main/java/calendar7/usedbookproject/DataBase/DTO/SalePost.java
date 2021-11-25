package calendar7.usedbookproject.DataBase.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "SalePost")
@DiscriminatorValue("Sale")
@Getter
@Setter
public class SalePost extends Post
{
    private String Title; // 책 제목
    private Date Postday; // 등록일
    private Integer Origin_Price; // 원래 가격
    private Integer Sale_Price; // 판매 가격
    private String Author; // 지은이
    private String Publisher; // 출판사
    private Boolean Negotiable; // 흥정가능여부
    private String Detail; // 설명
    private Integer Deal_Method; // 거래 방법, 직거래:0 택배:1
    private String KakaoTalk_url; // 카카오톡 오픈채팅방 링크
    private String Image_link; // 이미지 링크, 서버에 저장된 이미지의 절대경로.
    private String Deal_place; // 거래 장소, 거래방법을 직거래로 선택시 입력.
    private String Publication_date; // 출판일

    // 책의 상태
    private Integer Note;   // 필기, 없음:0 연필/샤프:1 볼팬/형광팬:2
    private Integer Underline; // 밑줄, 없음:0 연필/샤프:1 볼팬/형광팬:2
    private Boolean Damage; // 페이지 변색/훼손
    private Integer Cover; // 겉표지, 꺠끗함:0 약간 더러움:1 많이 더러움:2

    // JPA를 위한 기본 생성자
    protected SalePost() {}

    public SalePost(String UserID, String phonenumber, String nickName)
    {
        super(phonenumber, UserID, nickName);
        Postday = new Date();
    }

    public SalePost( String phonenumber, String userID, String nickName,
                     String title, Integer origin_Price,
                    Integer sale_Price, String author, String publisher,
                     Boolean isDeal, String detail, Integer deal_Method,
                    String image_link, Boolean damage, Integer note, Integer underline,
                    Integer cover, String deal_place, String publication_date)
    {
        super(phonenumber, userID, nickName);

        Postday = new Date();

        Title = title;
        Origin_Price = origin_Price;
        Sale_Price = sale_Price;
        Author = author;
        Publisher = publisher;
        Negotiable = isDeal;
        Detail = detail;
        Deal_Method = deal_Method;
        Image_link = image_link;
        Note = note;
        Underline = underline;
        Damage = damage;
        Cover = cover;
        Deal_place = deal_place;
        Publication_date = publication_date;
    }

    @Override
    public String toString() {
        return "SalePost{" +
                "Title='" + Title + '\'' +
                ", Postday=" + Postday +
                ", Origin_Price=" + Origin_Price +
                ", Sale_Price=" + Sale_Price +
                ", Author='" + Author + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Negotiable=" + Negotiable +
                ", Detail='" + Detail + '\'' +
                ", Deal_Method=" + Deal_Method +
                ", KakaoTalk_url='" + KakaoTalk_url + '\'' +
                ", Image_link='" + Image_link + '\'' +
                ", Deal_place='" + Deal_place + '\'' +
                ", Publication_date='" + Publication_date + '\'' +
                ", Note=" + Note +
                ", Underline=" + Underline +
                ", Damage=" + Damage +
                ", Cover=" + Cover +
                '}';
    }
}
