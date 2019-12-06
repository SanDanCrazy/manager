package fscut.manager.demo.service.serviceImpl;

import fscut.manager.demo.dao.CustomerRepository;
import fscut.manager.demo.dao.StoryEditionRepository;
import fscut.manager.demo.dao.StoryRepository;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.StoryEdition;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.service.StoryService;
import fscut.manager.demo.vo.StoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoryEditionRepository storyEditionRepository;

    @Override
    public Optional<Story> addStory(Story story) {

        StoryUPK storyUPK = getNewStoryUPK(story.getStoryUPK().getProductId());

        BeanUtils.copyProperties(storyUPK,story.getStoryUPK());

        storyRepository.save(story);

        StoryEdition storyEdition = new StoryEdition();

        BeanUtils.copyProperties(story,storyEdition);

        storyEditionRepository.save(storyEdition);

        return storyRepository.findById(storyUPK);
    }

    @Override
    public Optional<Story> editStory(Story story) {

        int edition = story.getStoryUPK().getEdition()+1;
        story.getStoryUPK().setEdition(edition);
        storyRepository.save(story);

        StoryEdition storyEdition = new StoryEdition();
        BeanUtils.copyProperties(story,storyEdition);
        storyEditionRepository.updateEdition(storyEdition);

        return storyRepository.findById(story.getStoryUPK());

    }

    @Override
    public Optional<Story> getStoryInfo(Story story) {
        return null;
    }

    @Override
    public void deleteStory(StoryUPK storyUPK) {

        storyRepository.deleteStories(storyUPK);

        storyRepository.deleteEditionByStoryUPK(storyUPK);
    }

    @Override
    public List<StoryUPK> getStoryEditionsByProductId(Integer productId) {

        List<StoryUPK> storyUPKS = storyEditionRepository.findStoryEditionsByProductId(productId);
        return storyUPKS;
    }

    /**
     * 获取一个产品的所有最新需求
     * @param
     * @return
     */
    @Override
    public List<Story> getStoriesByEditions(List<StoryUPK> storyUPK) {
        List<Story> storyList = new ArrayList<>();
        for (StoryUPK s: storyUPK
             ) {
            storyList.add(storyRepository.findStoryByEdition(s));
        }
        return storyList;
    }

    @Override
    public List<Story> getStoriesByProductId(Integer productId) {
        return getStoriesByEditions(getStoryEditionsByProductId(productId));
    }

    @Override
    public List<Story> getStoryHistory(StoryUPK storyUPK) {
        List<Story> stories = storyRepository.findStoriesByStoryUPK(storyUPK);
        return stories;
    }


    @Override
    public StoryUPK getNewStoryUPK(Integer productId) {
        StoryUPK storyUPK = new StoryUPK();
        Integer storyId = storyRepository.findLastedStoryId(productId);
        storyUPK.setProductId(productId);
        if(storyId == null){
            storyUPK.setStoryId(1);
            System.out.println("wow");
        }else{
            System.out.println("nice");
            storyUPK.setStoryId(storyId+1);
        }
        storyUPK.setEdition(1);
        return storyUPK;
    }

    @Override
    public Story convertStoryVO2Story(StoryVO storyVO) {
        Story story = new Story();
        BeanUtils.copyProperties(storyVO,story);
        story.getStoryUPK().setStoryId(storyVO.getStoryUPK().getStoryId());
        story.setDesignId(customerRepository.findIdByRealName(storyVO.getDesignName()));
        story.setDevId(customerRepository.findIdByRealName(storyVO.getDevelopName()));
        story.setTestId(customerRepository.findIdByRealName(storyVO.getTestName()));
        story.setEditId(storyVO.getCustomerId());
        return story;
    }
}
