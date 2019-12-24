package fscut.manager.demo.service;

import fscut.manager.demo.dto.StoryDetailDTO;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.vo.StoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    List<Story> getStoriesByProductId(Integer productId, Integer customerId);

    Story convertStoryVO2Story(StoryVO storyVO);


    /**
     * 需求名称模糊查询
     * @param storyName 需求名称
     * @return 需求列表
     */
    List<Story> getStoryByStoryNameLike(String storyName);

    /**
     * 客户描述模糊查询
     * @param description 客户描述
     * @return 需求列表
     */
    List<Story> getStoryByDescriptionLike(String description);

    /**
     * 用户输入搜索
     * @param input 用户输入
     * @param pageable 分页
     * @return 需求分页
     */
    Page<Story> searchStory(String input, Pageable pageable);
}
