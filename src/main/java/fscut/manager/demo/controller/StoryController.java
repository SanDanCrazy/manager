package fscut.manager.demo.controller;

import fscut.manager.demo.dto.StoryDetailDTO;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.UPK.StoryUPK;
import fscut.manager.demo.service.StoryService;
import fscut.manager.demo.vo.StoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin
public class StoryController {

    @Autowired
    private StoryService storyService;

    @PostMapping("newStory")
    public ResponseEntity<Story> newStory(StoryVO storyVO){
        Story story = storyService.convertStoryVO2Story(storyVO);

        Optional<Story> optional = storyService.addStory(story);
        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("editStory")
    public ResponseEntity<Story> editStory(StoryVO storyVO){
        Story story = storyService.convertStoryVO2Story(storyVO);

        Optional<Story> optional = storyService.editStory(story);
        return ResponseEntity.ok(optional.get());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<List<Story>> showProductStories(@PathVariable("id") Integer id){
        List<Story> stories = storyService.getStoriesByProductId(id);
        return ResponseEntity.ok(stories);
    }

    @GetMapping("Story")
    public ResponseEntity<StoryDetailDTO> showStoryInfo(StoryUPK storyUPK) {
        StoryDetailDTO storyDetailDTO = storyService.getStoryInfo(storyUPK);
        return ResponseEntity.ok(storyDetailDTO);
    }

    @GetMapping("history")
    public ResponseEntity<List<Story>> showStoryHistory(StoryUPK storyUPK) {
        List<Story> stories = storyService.getStoryHistory(storyUPK);
        return ResponseEntity.ok(stories);
    }

    @PutMapping("deleteStory")
    public ResponseEntity<String> deleteStory(StoryUPK storyUPK) {
        storyService.deleteStory(storyUPK);
        return ResponseEntity.ok("Delete successfully!");
    }



}
