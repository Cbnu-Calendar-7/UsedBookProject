package calendar7.usedbookproject.DataBase.DAO;


import calendar7.usedbookproject.DataBase.DTO.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>
{
}
