package calendar7.usedbookproject.service.Search;

import calendar7.usedbookproject.DataBase.DTO.SalePost;

import java.util.List;

public interface SearchService
{
    List<SalePost> SearchByTitle(String Title);
}
