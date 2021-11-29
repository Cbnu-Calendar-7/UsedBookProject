package calendar7.usedbookproject.Service.Search;

import calendar7.usedbookproject.DataBase.DTO.SalePost;

import java.util.List;

public interface SearchService
{
    List<SalePost> SearchByTitle(String Title);
}
