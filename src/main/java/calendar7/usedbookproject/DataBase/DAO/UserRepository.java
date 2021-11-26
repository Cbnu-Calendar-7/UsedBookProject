package calendar7.usedbookproject.DataBase.DAO;

import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
}
