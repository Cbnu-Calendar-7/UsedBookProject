package calendar7.usedbookproject.service.Search;

import calendar7.usedbookproject.DataBase.DAO.SalePostRepository;
import calendar7.usedbookproject.DataBase.DTO.SalePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSearchService implements SearchService
{

    private final SalePostRepository postRepo;

    @Autowired
    public BookSearchService(SalePostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<SalePost> SearchByTitle(String Title)
    {
        try
        {
            // 키워드에 아무것도 없을 때 예외반환
            if(Title == null)
            {
                throw new SearchException("Keyword is Null");
            }

            List<SalePost> FoundList = postRepo.findByTitleContaining(Title);

            // 찾은게 없으면 예외 반환
            if(FoundList == null)
            {
                throw new KeywordNotFoundException("Failed to search keyword!");
            }

            for(SalePost s : FoundList)
            {
                System.out.println(s.toString());
            }

            return FoundList;
        }
        catch (Exception e)
        {
            throw new SearchException("Failed to search keyword!", e);
        }


    }
}
