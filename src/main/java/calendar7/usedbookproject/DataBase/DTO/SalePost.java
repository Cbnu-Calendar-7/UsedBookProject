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
    private String ISBN; // ISBN
    private Boolean IsDeal; // 흥정가능여부
    private String Text; // 설명
    private Integer Deal_Method; // 거래 방법, 직거래:0(Deal_Method.Direct) 택배:1
    private String Image_link; // 이미지 링크, 서버에 저장된 이미지의 절대경로.

    // 책의 상태
    private Boolean IsOpen; // 개봉/미개봉(true/false) 여부
    private Integer Pollution; // 오염도  많음:0 적음:1 없음:2
    private Integer Written_underline; // 밑줄, 필기 여부, 많음:0 적음:1 없음:2

    // JPA를 위한 기본 생성자
    protected SalePost() {}

    public SalePost( String phonenumber, String userID, String nickName,
                     String title, Date postday, Integer origin_Price,
                    Integer sale_Price, String author, String publisher,
                    String isbn, Boolean isDeal, String text, Integer deal_Method,
                    String image_link, Boolean isOpen, Integer pollution, Integer written_underline)
    {
        super(phonenumber, userID, nickName);
        Title = title;
        Postday = postday;
        Origin_Price = origin_Price;
        Sale_Price = sale_Price;
        Author = author;
        Publisher = publisher;
        ISBN = isbn;
        IsDeal = isDeal;
        Text = text;
        Deal_Method = deal_Method;
        Image_link = image_link;
        IsOpen = isOpen;
        Pollution = pollution;
        Written_underline = written_underline;
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
                ", ISBN='" + ISBN + '\'' +
                ", IsDeal=" + IsDeal +
                ", Text='" + Text + '\'' +
                ", Deal_Method=" + Deal_Method +
                ", Image_link='" + Image_link + '\'' +
                ", IsOpen=" + IsOpen +
                ", Pollution=" + Pollution +
                ", Written_underline=" + Written_underline +
                '}';
    }
}
