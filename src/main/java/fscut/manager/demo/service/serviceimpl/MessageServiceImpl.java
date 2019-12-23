package fscut.manager.demo.service.serviceimpl;


import fscut.manager.demo.dao.CustomerRepository;
import fscut.manager.demo.dao.MessageRepository;
import fscut.manager.demo.entity.Message;
import fscut.manager.demo.entity.Story;
import fscut.manager.demo.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void addMessage(Story story, String action) {
        if(story == null)
            return;
        String content = String.format("%s %s %s 需求",customerRepository.findRealNameByCustomerId(story.getEditId()),action,story.getStoryName());
        Message message = new Message();
        BeanUtils.copyProperties(story,message);
        message.setContent(content);
        message = messageRepository.save(message);
        messageRepository.addCustomerMessage(message.getMessageId(), story.getEditId());
        messageRepository.addCustomerMessage(message.getMessageId(),story.getDesignId());
        messageRepository.addCustomerMessage(message.getMessageId(),story.getDevId());
        messageRepository.addCustomerMessage(message.getMessageId(),story.getTestId());
    }


    @Override
    public List<Message> getMessage(Integer customerId) {
        List<Integer> messageIds = messageRepository.getMessageId(customerId);
        List<Message> messageList = messageRepository.findMessagesByMessageIdIn(messageIds);
        return messageList;
    }

    @Override
    public List<Message> getMessage(String username){
        List<Integer> messageIds = messageRepository.getMessageId(username);
        List<Message> messageList = messageRepository.findMessagesByMessageIdIn(messageIds);
        return messageList;
    }

    @Override
    public void readMessage(Integer messageId, Integer customerId) {
        messageRepository.readMessage(messageId, customerId);
    }

    @Override
    public Integer getUnreadMessageNum(Integer customerId) {
        return messageRepository.getUnreadMessageNum(customerId);
    }

    @Override
    public Integer getUnreadMessageNum(String username) {
        return messageRepository.getUnreadMessageNum(username);
    }

    @Override
    public void deleteMessage(Integer messageId, Integer customerId) {
        messageRepository.deleteCustomerMessage(messageId, customerId);
    }

}
