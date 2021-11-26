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
    private String userID;  // 아이디

    private String password; // 패스워드
    private String email; // 이메일
    private String username; // 이름
    private String phonenumber; // 휴대폰번호
    private String nickname;  // 닉네임

    private String saleslist; // 판매리스트
    private String wishlist; // 찜리스트
    private String chatlist; // 채팅리스트


    public User(String UserID, String password, String email, String phonenumber, String nickname, String username)
    {
        this.userID = UserID;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.nickname = nickname;
        this.username = username;
        this.saleslist = "";
        this.wishlist = "";
        this.chatlist = "";
    }

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "UserID='" + userID + '\'' +
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
