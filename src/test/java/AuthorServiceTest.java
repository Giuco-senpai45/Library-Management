import com.tpjad.lbm.LibraryManagementApplication;
import com.tpjad.lbm.entities.Author;
import com.tpjad.lbm.service.AuthorService;
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
public class AuthorServiceTest {
    private final AuthorService authorService;

    @Autowired
    public AuthorServiceTest(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Test
    public void testAuthorService() {
        Author author = new Author("Test junit5", "Test description");
        authorService.createAuthor(author);
        int authorCount = authorService.findAllAuthors().size();
        Author author1=authorService.findAuthorById(4L);
        assertEquals("Test junit5",author1.getName());
        assertEquals(4, authorCount);
        author.setName("Test Update");
        authorService.updateAuthor(author);
        String get_name=authorService.findAuthorById(author.getId()).getName();
        assertEquals("Test Update",get_name);
        authorService.deleteAuthor(4L);
        authorCount = authorService.findAllAuthors().size();
        assertEquals(3,authorCount);

    }
}
