import com.tpjad.lbm.LibraryManagementApplication;
import com.tpjad.lbm.entities.Book;
import com.tpjad.lbm.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
@SpringBootTest(classes = LibraryManagementApplication.class)
@ActiveProfiles("tc")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class BookServiceTest {
    private final BookService bookService;
    @Autowired
    public BookServiceTest(BookService bookService) {
        this.bookService = bookService;
    }


    @Test
    public void testBookService() {
        Book book = new Book("Test junit5", "Test name", "Test serial name", "Test description");

        bookService.createBook(book);
        int bookCount = bookService.findAllBooks().size();
        Book book1=bookService.findBookById(4L);
        assertEquals("Test name",book1.getName());
        assertEquals(4, bookCount);
        book.setName("Test Update");
        bookService.updateBook(book);
        bookService.updateBookFavouriteStatus(4L,true);
        String get_name=bookService.findBookById(book.getId()).getName();
        Boolean get_fav=bookService.findBookById(book.getId()).getFavourite();
        assertEquals("Test Update",get_name);
        assertEquals(true,get_fav);
        bookService.deleteBook(4L);
        bookCount = bookService.findAllBooks().size();
        assertEquals(3,bookCount);

    }
}
