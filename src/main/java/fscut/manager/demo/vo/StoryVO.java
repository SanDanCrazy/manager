package fscut.manager.demo.vo;

import fscut.manager.demo.entity.UPK.StoryUPK;
import lombok.Data;

import java.sql.Date;

@Data
public class StoryVO {

    private StoryUPK storyUPK;

    private Integer customerId;

    private Integer origin;

    private Date putTime;

    private String storyName;

    private Integer storyStatus;

    private String description;

    private String conclusion;

    private String designName;

    private String developName;

    private String testName;

    private Date testTime;

    private Date updateTime;

}
