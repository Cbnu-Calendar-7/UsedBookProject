package calendar7.usedbookproject.DataBase.DAO;

import calendar7.usedbookproject.DataBase.DTO.User;
import calendar7.usedbookproject.Service.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserIdentity(String UserID);
}
