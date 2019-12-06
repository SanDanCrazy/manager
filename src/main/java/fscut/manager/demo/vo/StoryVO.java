package fscut.manager.demo.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class StoryVO {

    private Integer productId;

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

}
