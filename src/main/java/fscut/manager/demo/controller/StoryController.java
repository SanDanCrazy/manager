package fscut.manager.demo.controller;

import fscut.manager.demo.dto.StoryDetailDTO;
import fscut.manager.demo.dto.UserDto;
import fscut.manager.demo.entity.Customer;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.exception.CustomerNoAuthorityException;
import fscut.manager.demo.service.CustomerService;
import fscut.manager.demo.service.MessageService;
import fscut.manager.demo.service.StoryService;
import fscut.manager.demo.service.serviceimpl.UserService;
import fscut.manager.demo.util.CsvUtils;
import fscut.manager.demo.util.websocket.WebSocketServer;
import fscut.manager.demo.vo.StoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * 该类中函数第一个参数必须含有标识产品id的属性，例如storyVO,storyUPK,productId
 */


@RestController
@CrossOrigin
@RequestMapping("story")
public class StoryController {

    @Resource
    private StoryService storyService;

    @Resource
    private UserService userService;

    @Resource
    private CustomerService customerService;

    @Resource
    private MessageService messageService;

    @PostMapping("newStory")
    public ResponseEntity<Story> newStory(@RequestBody StoryVO storyVO){
        //可以把验证用户做成一个切面
        //userService.userAllowed(storyVO.getStoryUPK().getProductId());

        Story story = storyService.convertStoryVO2Story(storyVO);

        Optional<Story> optional = storyService.addStory(story);
        messageService.addMessage(optional.get(),"新建");


        try{
            WebSocketServer.sendInfo(messageService.getUnreadMessageNum(optional.get().getDesignId()),
                    customerService.getUsernameById(optional.get().getDesignId()));
        }catch (Exception e){
            return null;
        }
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("editStory")
    public ResponseEntity<Story> editStory(@RequestBody StoryVO storyVO){
        //userService.userAllowed(storyVO.getStoryUPK().getProductId());

        Story story = storyService.convertStoryVO2Story(storyVO);
        Optional<Story> optional = storyService.editStory(story);
        return ResponseEntity.ok(optional.get());
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<Page<Story>> showProductStories(@PathVariable("productId") Integer productId, Integer page, Integer size) throws CustomerNoAuthorityException {
       // userService.userAllowed(productId);

        Subject subject = SecurityUtils.getSubject();
        UserDto user = (UserDto) subject.getPrincipal();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Story> storyPage = storyService.getStoriesByProductId(productId, user.getUserId(), pageRequest);

        return ResponseEntity.ok(storyPage);
    }

    @GetMapping("Story")
    public ResponseEntity<StoryDetailDTO> showStoryInfo(Integer productId, Integer storyId, Integer edition){
        //userService.userAllowed(productId);

        StoryUPK storyUPK = new StoryUPK(productId, storyId, edition);
        StoryDetailDTO storyDetailDTO = storyService.getStoryInfo(storyUPK);
        return ResponseEntity.ok(storyDetailDTO);
    }

    @PostMapping("history")
    public ResponseEntity<List<Story>> showStoryHistory(@RequestBody StoryUPK storyUPK){
        //userService.userAllowed(storyUPK.getProductId());

        List<Story> stories = storyService.getStoryHistory(storyUPK);
        return ResponseEntity.ok(stories);
    }

    @DeleteMapping("deleteStory")
    public ResponseEntity<String> deleteStory(@RequestBody StoryUPK storyUPK){
        //userService.userAllowed(storyUPK.getProductId());

        storyService.deleteStory(storyUPK);
        return ResponseEntity.ok("Delete successfully!");
    }

    @PostMapping("selectStory")
    public ResponseEntity<Page<Story>> selectStory(Integer productId, String startTime, String endTime, String origin, String userInput, Integer page, Integer size)  {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Story> stories = storyService.selectStory(productId, startTime, endTime, origin, userInput, pageRequest);
        return ResponseEntity.ok(stories);
    }

    @GetMapping("download")
    public ResponseEntity<Void> download(Integer productId,HttpServletResponse response) throws IOException{
        //userService.userAllowed(productId);
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition","attachment;filename=writeCSV.csv");
        Subject subject = SecurityUtils.getSubject();
        UserDto user = (UserDto) subject.getPrincipal();
        CsvUtils.download(storyService.getStoriesByProductId(productId,user.getUserId()), response);
        return ResponseEntity.ok(null);
    }
}
