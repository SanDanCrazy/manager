package fscut.manager.demo.controller;


import fscut.manager.demo.dto.UserDto;
import fscut.manager.demo.service.CustomerService;
import fscut.manager.demo.service.MessageService;
import fscut.manager.demo.vo.MessageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("message")
@Api(value = "消息接口",tags = {"消息相关接口"},produces = "消息",consumes = "nothing",protocols = "http")
public class MessageController{

     @Resource
     private MessageService messageService;

     @Resource
     private CustomerService customerService;

     @GetMapping("getNum")
     public ResponseEntity<Integer> getUnreadMessageNum() {
          Subject subject = SecurityUtils.getSubject();
          UserDto user = (UserDto) subject.getPrincipal();
          return ResponseEntity.ok(messageService.getUnreadMessageNum(user.getUserId()));
     }

     @ApiOperation(value = "获取消息列表",notes = "无需传入参数，根据token携带的用户信息查询用户消息")
     @GetMapping("getMessageList")
     public ResponseEntity<List<MessageVO>> getMessageList(){
          Subject subject = SecurityUtils.getSubject();
          UserDto user = (UserDto) subject.getPrincipal();
          List<MessageVO> messageList = messageService.getMessage(user.getUserId());
          return ResponseEntity.ok(messageList);
     }

     @GetMapping("readMessage")
     public ResponseEntity<Integer> readMessage(Integer messageId, String username) {
         Integer customerId = customerService.getIdByUsername(username);
          Integer res = messageService.readMessage(messageId, customerId);
          return ResponseEntity.ok(res);
     }

     @GetMapping("readAll")
     public ResponseEntity<Integer> readAll(String username) {
          Integer customerId = customerService.getIdByUsername(username);
          Integer res = messageService.readAll(customerId);
          return ResponseEntity.ok(res);
     }

     @DeleteMapping("deleteMessage")
     public ResponseEntity<Integer> deleteMessage(Integer messageId, String username) {
          Integer customerId = customerService.getIdByUsername(username);
          Integer res = messageService.deleteMessage(messageId, customerId);
          return ResponseEntity.ok(res);
     }

     @DeleteMapping("deleteAll")
     public ResponseEntity<Integer> deleteMessage(String username) {
          Integer customerId = customerService.getIdByUsername(username);
          Integer res = messageService.deleteAll(customerId);
          return ResponseEntity.ok(res);
     }


}
