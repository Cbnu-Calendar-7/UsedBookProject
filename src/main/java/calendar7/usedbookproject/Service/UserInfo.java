package calendar7.usedbookproject.Service;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="user_info")
public class UserInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "userIdentity", unique = true)
    private String userIdentity;  // 아이디
    @Column(name = "password")
    private String password; // 패스워드
    @Column(name = "email", unique = true)
    private String email; // 이메일
    @Column(name = "username")
    private String username; // 이름
    @Column(name = "phonenumber")
    private String phonenumber; // 휴대폰번호
    @Column(name = "nickname")
    private String nickname;  // 닉네임
    @Column(name = "saleslist")
    private String saleslist; // 판매리스트
    @Column(name = "wishlist")
    private String wishlist; // 찜리스트
    @Column(name = "chatlist")
    private String chatlist; // 채팅리스트

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="user_id"))
    private Set<UserAuth> authorities;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
