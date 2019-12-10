package fscut.manager.demo.enums;

import lombok.Getter;

@Getter
public enum  StoryStatusEnum implements CodeEnum {
    NEW(0, "新需求"),
    FINISHED(1, "完成"),
    CANCEL(2, "已取消")
    ;

    private Integer code;

    private String message;

    StoryStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
