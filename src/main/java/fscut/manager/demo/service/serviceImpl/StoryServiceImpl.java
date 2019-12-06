package fscut.manager.demo.service.serviceImpl;

import fscut.manager.demo.dao.CustomerRepository;
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

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Optional<Story> addStory(Story story) {

        StoryUPK storyUPK = getNewStoryUPK(story.getStoryUPK().getProductId());

        BeanUtils.copyProperties(storyUPK,story.getStoryUPK());

        storyRepository.save(story);

        StoryEdition storyEdition = new StoryEdition();

        BeanUtils.copyProperties(story,storyEdition);

        storyRepository.updateEdition(storyEdition);

        Optional<Story> optional = Optional.ofNullable(story);
        return optional;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Optional<Story> editStory(Story story) {

        int edition = story.getStoryUPK().getEdition()+1;
        story.getStoryUPK().setEdition(edition);
        storyRepository.save(story);

        StoryEdition storyEdition = new StoryEdition();
        BeanUtils.copyProperties(story,storyEdition);
        storyRepository.updateEdition(storyEdition);
        return Optional.ofNullable(story);

    }

    @Override
    public Optional<Story> getStoryInfo(Story story) {
        return null;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteStory(StoryUPK storyUPK) {

        storyRepository.deleteById(storyUPK);

        storyRepository.deleteEditionByStoryUPK(storyUPK);
    }

    @Override
    public List<StoryEdition> getStoryEditionsByProductId(Integer productId) {
        List<StoryEdition> storyEditions = storyRepository.findStoryEditionsByProductId(productId);
        return storyEditions;
    }

    /**
     * 获取一个产品的所有最新需求
     * @param storyEditions
     * @return
     */
    @Override
    public List<Story> getStoriesByEditions(List<StoryEdition> storyEditions) {
        List<Story> storyList = new ArrayList<>();
        for (StoryEdition storyEdition: storyEditions
             ) {
            storyList.add(storyRepository.findStoryByEdition(storyEdition));
        }
        return null;
    }

    @Override
    public List<Story> getStoriesByProductId(Integer productId) {
        return getStoriesByEditions(getStoryEditionsByProductId(productId));
    }

    @Override
    public Optional<List<Story>> getStoryHistory(StoryUPK storyUPK) {
        Optional<List<Story>> stories = storyRepository.findStoriesByStoryUPK(storyUPK);
        return stories;
    }


    @Override
    public StoryUPK getNewStoryUPK(Integer productId) {
        StoryUPK storyUPK = new StoryUPK();
        Integer storyId = storyRepository.findLastedStoryId(productId);
        storyUPK.setProductId(productId);
        if(storyId <= 0){
            storyUPK.setStoryId(1);
        }
        storyUPK.setStoryId(storyId);
        storyUPK.setEdition(1);
        return storyUPK;
    }

    @Override
    public Story convertStoryVO2Story(StoryVO storyVO) {
        Story story = new Story();
        story.getStoryUPK().setProductId(storyVO.getProductId());
        BeanUtils.copyProperties(storyVO,story);
        story.setDesignId(customerRepository.findIdByRealName(storyVO.getDesignName()));
        story.setDevId(customerRepository.findIdByRealName(storyVO.getDevelopName()));
        story.setTestId(customerRepository.findIdByRealName(storyVO.getTestName()));
        story.setEditId(storyVO.getCustomerId());
        return story;
    }
}
