package calendar7.usedbookproject.DataBase.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorColumn(name = "Post_Type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PostID;
    private String Phonenumber;
    private String UserID;
    private String NickName;

    protected Post() {}

    public Post(String phonenumber, String userID, String nickName)
    {
        Phonenumber = phonenumber;
        UserID = userID;
        NickName = nickName;
    }
}
