package fscut.manager.demo.entity.UPK;

import com.fasterxml.jackson.annotation.JsonView;
import fscut.manager.demo.entity.Story;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@ApiModel(value = "需求唯一标识")
public class StoryUPK implements Serializable{

    @JsonView(Story.StorySimpleView.class)
    @ApiModelProperty(value = "产品ID", example = "1",required = true)
    private Integer productId;

    @JsonView(Story.StorySimpleView.class)
    @ApiModelProperty(value = "需求ID",example = "1",required = true)
    private Integer storyId;

    @JsonView(Story.StorySimpleView.class)
    @ApiModelProperty(value = "版本ID", example = "1", required = true)
    private Integer edition;

    public StoryUPK() {
    }

    public StoryUPK(Integer productId, Integer storyId, Integer edition) {
        this.productId = productId;
        this.storyId = storyId;
        this.edition = edition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryUPK storyUPK = (StoryUPK) o;

        if (!productId.equals(storyUPK.productId)) return false;
        if (!storyId.equals(storyUPK.storyId)) return false;
        return edition.equals(storyUPK.edition);
    }

    @Override
    public int hashCode() {
        int result = productId.hashCode();
        result = 31 * result + storyId.hashCode();
        result = 31 * result + edition.hashCode();
        return result;
    }
}
