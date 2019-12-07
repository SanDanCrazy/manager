package fscut.manager.demo.service;

import fscut.manager.demo.dto.StoryDetailDTO;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.StoryEdition;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.vo.StoryVO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface StoryService {

    Optional<Story> addStory(Story story);

    Optional<Story> editStory(Story story);

    void deleteStory(StoryUPK storyUPK);

    List<StoryUPK> getStoryEditionsByProductId(Integer productId);

    List<Story> getStoriesByEditions(List<StoryUPK> storyUPKS);

    List<Story> getStoryHistory(StoryUPK storyUPK);

    StoryUPK getNewStoryUPK(Integer productId);

    StoryDetailDTO getStoryInfo(StoryUPK storyUPK);

    List<Story> getStoriesByProductId(Integer productId);

    Story convertStoryVO2Story(StoryVO storyVO);

    //创建一个返回需求历史信息的函数
}
