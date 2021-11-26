package calendar7.usedbookproject.DataBase.DAO;


import calendar7.usedbookproject.DataBase.DTO.SalePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalePostRepository extends JpaRepository<SalePost, Long>
{
    List<SalePost> findByTitleContaining(String title);

}
