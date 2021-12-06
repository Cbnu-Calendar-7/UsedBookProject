package calendar7.usedbookproject.Service.Login;

import calendar7.usedbookproject.DataBase.DAO.UserRepository;
import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return null이면 로그인 실패
     */
    public User login(String loginId, String password) {

        return userRepository.findById(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
