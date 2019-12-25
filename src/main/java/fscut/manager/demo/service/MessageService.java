package fscut.manager.demo.service;

import fscut.manager.demo.entity.Message;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.vo.MessageVO;

import java.util.List;

public interface MessageService {
    void addMessage(Story story, String action);
    void readMessage(Integer messageId, Integer customerId);
    Integer getUnreadMessageNum(Integer customerId);
    Integer getUnreadMessageNum(String username);
    void deleteMessage(Integer messageId, Integer customerId);
    List<MessageVO> getMessage(Integer customerId);
}
