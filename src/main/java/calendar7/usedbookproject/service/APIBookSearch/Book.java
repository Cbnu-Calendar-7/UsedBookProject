package calendar7.usedbookproject.Service.APIBookSearch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book
{
    private String title;
    private String author;
    private String isbn10;
    private String isbn13;
    private String publisher;
    private String image;
    private String link;
    private String pubdate;
    private String price;

    @Override
    public String toString() {
        return
                "title = " + title + '\n' +
                "author = " + author + '\n' +
                "isbn10 = " + isbn10 + '\n' +
                "isbn13 = " + isbn13 + '\n' +
                "publisher = " + publisher + '\n' +
                "image = " + image + '\n' +
                "link = " + link + '\n' +
                "pubdate = " + pubdate + '\n' +
                "price = " + price;
    }
}
