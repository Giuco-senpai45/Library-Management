import com.tpjad.lbm.LibraryManagementApplication;
import com.tpjad.lbm.entities.Category;
import com.tpjad.lbm.entities.Category;
import com.tpjad.lbm.service.CategoryService;
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
public class CategoryServiceTest {
    private final CategoryService categoryService;

    @Autowired
    public CategoryServiceTest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Test
    public void testCategoryService() {
        Category category = new Category("Test junit5");
        categoryService.createCategory(category);
        int categoryCount = categoryService.findAllCategories().size();
        Category category1=categoryService.findCategoryById(4L);
        assertEquals("Test junit5",category1.getName());
        assertEquals(4, categoryCount);
        category.setName("Test Update");
        categoryService.updateCategory(category);
        String get_name=categoryService.findCategoryById(category.getId()).getName();
        assertEquals("Test Update",get_name);
        categoryService.deleteCategory(4L);
        categoryCount = categoryService.findAllCategories().size();
        assertEquals(3,categoryCount);

    }
}

