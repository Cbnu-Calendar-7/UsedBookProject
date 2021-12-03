package calendar7.usedbookproject.Service.Admin;

import calendar7.usedbookproject.DataBase.DAO.UserRepository;
import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoginService {

    private final UserRepository userRepository;

    @Autowired
    public AdminLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

}
