package fscut.manager.demo.service;

import fscut.manager.demo.dto.CustomerListDTO;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.vo.StoryDetailVO;
import fscut.manager.demo.vo.StoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface StoryService {

    Optional<Story> addStory(Story story);

    Optional<Story> editStory(Story story);

    Integer deleteStory(StoryUPK storyUPK);

    List<StoryUPK> getStoryEditionsByProductId(Integer productId);

    List<Story> getStoriesByEditions(List<StoryUPK> storyUPKS);

    Page<Story> getStoriesByEditions(List<StoryUPK> storyUPKS, Pageable pageable);

    List<Story> getStoryHistory(StoryUPK storyUPK);

    StoryUPK getNewStoryUPK(Integer productId);

    StoryDetailVO getStoryInfo(StoryUPK storyUPK);

    Page<Story> getStoriesByProductId(Integer productId, Integer customerId, Pageable pageable);

    List<Story> getStoriesByProductId(Integer productId, Integer customerId);

    Story convertStoryVO2Story(StoryVO storyVO);

    CustomerListDTO getCustomers(Integer productId);

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
     * 筛选即搜索需求
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @param origin 来源
     * @param input 用户输入
     * @return 需求分页
     */
    Page<Story> selectStory(Integer productId, String startTime, String endTime, String origin, String input, Pageable pageable);

    /**
     * 根据产品id和需求id查找需求
     * @param productId 产品id
     * @param storyId 需求id
     * @return 需求
     */
    List<Story> getStoryByStoryId(Integer productId, Integer storyId);
}
