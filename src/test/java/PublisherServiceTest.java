import com.tpjad.lbm.LibraryManagementApplication;
import com.tpjad.lbm.entities.Publisher;
import com.tpjad.lbm.entities.Publisher;
import com.tpjad.lbm.service.PublisherService;
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
public class PublisherServiceTest {
    private final PublisherService publisherService;

    @Autowired
    public PublisherServiceTest(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Test
    public void testPublisherService() {
        Publisher publisher = new Publisher("Test junit5");
        publisherService.createPublisher(publisher);
        int publisherCount = publisherService.findAllPublishers().size();
        Publisher publisher1=publisherService.findPublisherById(4L);
        assertEquals("Test junit5",publisher1.getName());
        assertEquals(4, publisherCount);
        publisher.setName("Test Update");
        publisherService.updatePublisher(publisher);
        String get_name=publisherService.findPublisherById(publisher.getId()).getName();
        assertEquals("Test Update",get_name);
        publisherService.deletePublisher(4L);
        publisherCount = publisherService.findAllPublishers().size();
        assertEquals(3,publisherCount);

    }
}


