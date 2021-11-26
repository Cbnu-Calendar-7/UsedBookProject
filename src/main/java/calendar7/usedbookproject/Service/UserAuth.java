package calendar7.usedbookproject.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
//@Table(name = "user_auth")
@IdClass(UserAuth.class)
public class UserAuth implements GrantedAuthority {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @ColumnDefault("ROLE_USER")
    private String authority;
}
