package fscut.manager.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fscut.manager.demo.dto.StoryDetailDTO;
import fscut.manager.demo.dto.UserDto;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.service.StoryService;
import fscut.manager.demo.service.serviceImpl.StoryServiceImpl;
import fscut.manager.demo.service.serviceImpl.UserService;
import fscut.manager.demo.util.RequestWrapper;
import fscut.manager.demo.vo.StoryVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("product")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("newStory")
    public ResponseEntity<Story> newStory(@RequestBody StoryVO storyVO){
        userService.userAllowed(storyVO.getStoryUPK().getProductId());

        Story story = storyService.convertStoryVO2Story(storyVO);

        Optional<Story> optional = storyService.addStory(story);
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("editStory")
    public ResponseEntity<Story> editStory(@RequestBody StoryVO storyVO){
        userService.userAllowed(storyVO.getStoryUPK().getProductId());

        Story story = storyService.convertStoryVO2Story(storyVO);
        Optional<Story> optional = storyService.editStory(story);
        return ResponseEntity.ok(optional.get());
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Story>> showProductStories(@PathVariable("id") Integer id){
        userService.userAllowed(id);

        Subject subject = SecurityUtils.getSubject();
        UserDto user = (UserDto) subject.getPrincipal();
        List<Story> stories = storyService.getStoriesByProductId(id, user.getUserId());

        return ResponseEntity.ok(stories);
    }

    @GetMapping("Story")
    public ResponseEntity<StoryDetailDTO> showStoryInfo(Integer productId, Integer storyId, Integer edition){
        userService.userAllowed(productId);

        StoryUPK storyUPK = new StoryUPK(productId, storyId, edition);
        StoryDetailDTO storyDetailDTO = storyService.getStoryInfo(storyUPK);
        return ResponseEntity.ok(storyDetailDTO);
    }

    @GetMapping("history")
    public ResponseEntity<List<Story>> showStoryHistory(@RequestBody StoryUPK storyUPK){
        userService.userAllowed(storyUPK.getProductId());

        List<Story> stories = storyService.getStoryHistory(storyUPK);
        return ResponseEntity.ok(stories);
    }

    @DeleteMapping("deleteStory")
    public ResponseEntity<String> deleteStory(@RequestBody StoryUPK storyUPK){
        userService.userAllowed(storyUPK.getProductId());

        storyService.deleteStory(storyUPK);
        return ResponseEntity.ok("Delete successfully!");
    }


}
