package fscut.manager.demo.service.serviceimpl;

import fscut.manager.demo.dao.CustomerRepository;
import fscut.manager.demo.dao.StoryEditionRepository;
import fscut.manager.demo.dao.StoryRepository;
import fscut.manager.demo.dto.StoryDetailDTO;
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
    @Transactional
    public Optional<Story> addStory(Story story) {
        StoryUPK storyUPK = getNewStoryUPK(story.getStoryUPK().getProductId());
        BeanUtils.copyProperties(storyUPK, story.getStoryUPK());
        storyRepository.save(story);
        StoryEdition storyEdition = new StoryEdition();
        BeanUtils.copyProperties(story, storyEdition);
        storyEditionRepository.save(storyEdition);
        return storyRepository.findById(storyUPK);
    }

    @Override
    @Transactional
    public Optional<Story> editStory(Story story) {

        int edition = story.getStoryUPK().getEdition() + 1;
        story.getStoryUPK().setEdition(edition);
        storyRepository.save(story);

        StoryEdition storyEdition = new StoryEdition();
        BeanUtils.copyProperties(story, storyEdition);
        storyEditionRepository.updateEdition(storyEdition);

        return storyRepository.findById(story.getStoryUPK());

    }

    @Override
    public StoryDetailDTO getStoryInfo(StoryUPK storyUPK) {
        List<Story> storyList = getStoryHistory(storyUPK);
        StoryDetailDTO storyDetailDTO = new StoryDetailDTO();
        Optional<Story> story = storyRepository.findById(storyUPK);
        storyDetailDTO.setStory(story.get());

        for (int i = 0; i < storyList.size()-1; i++) {
            Story tmp = getDifferenceBetween2Stories(storyList.get(i), storyList.get(i+1));
            storyDetailDTO.getStoryList().add(tmp);
        }
        return storyDetailDTO;
    }

    public Story getDifferenceBetween2Stories(Story story1, Story story2){
        Story result = new Story();
        if(!compareString(story1.getConclusion(), story2.getConclusion())) {
            result.setConclusion(story1.getConclusion());
        }
        if(!compareString(story1.getDescription(), story2.getDescription())) {
            result.setDescription(story1.getDescription());
        }
        if(!story1.getDesignId().equals(story2.getDesignId())) {
            result.setDesignId(story1.getDesignId());
        }
        if(!story1.getDevId().equals(story2.getDevId())) {
            result.setDevId(story1.getDevId());
        }
        if(!story1.getTestId().equals(story2.getTestId())) {
            result.setTestId(story1.getTestId());
        }
        if(!compareString(story1.getOrigin(), story2.getOrigin())) {
            result.setOrigin(story1.getOrigin());
        }
        if(!story1.getPutTime().equals(story2.getPutTime())) {
            result.setPutTime(story1.getPutTime());
        }
        if(!compareString(story1.getStoryName(), story2.getStoryName())) {
            result.setStoryName(story1.getStoryName());
        }
        if(!story1.getStoryStatus().equals(story2.getStoryStatus())) {
            result.setStoryStatus(story1.getStoryStatus());
        }
        result.setEditId(story1.getEditId());
        result.setUpdateTime(story1.getUpdateTime());
        return result;
    }

    /**
     * 相同返回true
     * @param str1
     * @param str2
     * @return
     */
    boolean compareString(String str1, String str2){
        return ((str1 == str2) || (str1 != null && str1.equals(str2)));
    }

    @Override
    @Transactional
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
        for (StoryUPK s : storyUPK
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
        if (storyId == null) {
            storyUPK.setStoryId(1);
        } else {
            storyUPK.setStoryId(storyId + 1);
        }
        storyUPK.setEdition(1);
        return storyUPK;
    }

    @Override
    public Story convertStoryVO2Story(StoryVO storyVO) {
        Story story = new Story();
        BeanUtils.copyProperties(storyVO, story);
        story.getStoryUPK().setStoryId(storyVO.getStoryUPK().getStoryId());
        story.setDesignId(customerRepository.findIdByRealName(storyVO.getDesignName()));
        story.setDevId(customerRepository.findIdByRealName(storyVO.getDevelopName()));
        story.setTestId(customerRepository.findIdByRealName(storyVO.getTestName()));
        story.setEditId(storyVO.getCustomerId());
        return story;
    }
}
