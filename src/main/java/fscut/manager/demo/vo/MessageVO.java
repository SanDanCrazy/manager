package fscut.manager.demo.vo;

import fscut.manager.demo.entity.UPK.StoryUPK;
import lombok.Data;

import java.util.Date;

@Data
public class MessageVO {

    private Integer messageId;
    private StoryUPK storyUPK;
    private String content;
    private Date createdTime;
    private Integer customerId;
    private boolean checked;

    public MessageVO(Integer messageId, StoryUPK storyUPK, String content, Date createdTime, Integer customerId, boolean checked) {
        this.messageId = messageId;
        this.storyUPK = storyUPK;
        this.content = content;
        this.createdTime = createdTime;
        this.customerId = customerId;
        this.checked = checked;
    }
}
