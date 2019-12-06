package fscut.manager.demo.entity;

import fscut.manager.demo.entity.UPK.StoryUPK;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "story_edition")
public class StoryEdition {

    @EmbeddedId
    private StoryUPK storyUPK;

    @Column(name = "edit_id")
    private Integer editId;
}
