package calendar7.usedbookproject.DataBase.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User
{
    @Id
    private String UserID;  // 아이디

    private String password; // 패스워드
    private String email; // 이메일
    private String username; // 이름
    private String phonenumber; // 휴대폰번호
    private String nickname;  // 닉네임

    private String saleslist; // 판매리스트
    private String wishlist; // 찜리스트
    private String chatlist; // 채팅리스트

    // JPA를 위한 기본생성자
    protected User() {}

    public User(String UserID, String password, String email, String phonenumber, String nickname, String username)
    {
        this.UserID = UserID;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.nickname = nickname;
        this.username = username;
        this.saleslist = "";
        this.wishlist = "";
        this.chatlist = "";
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID='" + UserID + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", nickname='" + nickname + '\'' +
                ", saleslist='" + saleslist + '\'' +
                ", wishlist='" + wishlist + '\'' +
                ", chatlist='" + chatlist + '\'' +
                '}';
    }
}
