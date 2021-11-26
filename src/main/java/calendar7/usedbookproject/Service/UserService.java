package calendar7.usedbookproject.Service;

import calendar7.usedbookproject.DataBase.DAO.UserRepository;
import calendar7.usedbookproject.DataBase.DTO.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;


@Service
@Builder
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public Optional<UserInfo> findUser(String identity){
        return userRepository.findByUserIdentity(identity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user =  userRepository.findByUserIdentity(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return user;
    }

    public UserInfo save(UserInfo user) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));
//        user.setEnabled(true);
//
//        return userRepository.save(UserInfo.builder()
//                .userId(user.getUserId())
//                .userIdentity(user.getUserIdentity())
//                .email(user.getEmail())
//                .username(user.getUsername())
//                .phonenumber(user.getPhonenumber())
//                .nickname(user.getNickname())
//                .saleslist(user.getSaleslist())
//                .wishlist(user.getWishlist())
//                .chatlist(user.getChatlist())
//                .password(user.getPassword())
//                .authorities(user.getAuthorities())
//                .enabled(true)
//                .build());
        return userRepository.save(user);
    }

    public void addAuthority(Long userId, String authority){
        userRepository.findById(userId).ifPresent(user->{
            UserAuth newRole = new UserAuth(user.getUserId(), authority);
            if(user.getAuthorities() == null){
                HashSet<UserAuth> authorities = new HashSet<>();
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            }else if(!user.getAuthorities().contains(newRole)){
                HashSet<UserAuth> authorities = new HashSet<>();
                authorities.addAll(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
                save(user);
            }
        });
    }
}
