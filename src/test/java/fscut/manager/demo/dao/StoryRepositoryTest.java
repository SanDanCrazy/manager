package fscut.manager.demo.dao;

import fscut.manager.demo.entity.Story;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
class StoryRepositoryTest {

    @Autowired
    private StoryRepository storyRepository;

    @Test
    public void findByNameContains() throws Exception {
        PageRequest pageRequest = new PageRequest();
        Page<Story> story = storyRepository.findByNameContains("1", pageRequest);
        System.out.println(story.getTotalElements());
        Assert.assertNotEquals(0, story.getTotalElements());
    }

}