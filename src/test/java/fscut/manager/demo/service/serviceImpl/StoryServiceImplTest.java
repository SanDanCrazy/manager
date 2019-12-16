package fscut.manager.demo.service.serviceImpl;

import fscut.manager.demo.entity.Story;
import fscut.manager.demo.service.StoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoryServiceImplTest {

    @Autowired
    private StoryService storyService;

    @Test
    public void testGetStoryByStoryNameLike() {
        List<Story> storyList = storyService.getStoryByStoryNameLike("次");
        Assert.assertNotEquals(0, storyList.size());
    }

    @Test
    public void testGetStoryByDescriptionLike() {
        List<Story> storyList = storyService.getStoryByDescriptionLike("o");
        Assert.assertNotEquals(0, storyList.size());
    }

    @Test
    public void testSearchStory() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Story> storyPage = storyService.searchStory("次", pageRequest);
        System.out.println(storyPage.getTotalElements());
        Assert.assertNotEquals(0, storyPage.getTotalElements());
    }

}