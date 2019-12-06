package fscut.manager.demo.dao;

import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.StoryEdition;
import fscut.manager.demo.entity.UPK.StoryUPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface StoryRepository extends JpaRepository<Story, StoryUPK>{

    @Query(value = "select story_id from story_edition where product_id = ?1 order by story_id DESC limit 1",nativeQuery = true)
    Integer findLastedStoryId(Integer productId);

    @Modifying
    @Transactional
    @Query(value = "update story_edition set edition = #{#storyEdition.storyUPK.edition},edit_id = #{#storyEdition.editId} where " +
            "product_id = #{#storyEdition.storyUPK.productId} and story_id = #{#storyEdition.storyUPK.storyId}",nativeQuery = true)
    Void updateEdition(StoryEdition storyEdition);

    @Modifying
    @Transactional
    @Query(value = "delete from story_edition where product_id = #{#storyEdition.storyUPK.productId} and story_id = #{#storyEdition.storyUPK.storyId}", nativeQuery = true)
    Integer deleteEditionByStoryUPK(StoryUPK storyUPK);

    @Query(value = "select * from story_edition where product_id = ?1 order by story_id desc ", nativeQuery = true)
    List<StoryEdition> findStoryEditionsByProductId(Integer productId);

    @Query(value = "select * from story where product_id = #{#storyUPK.productId} and story_id = #{#storyUPK.storyId} order by edition desc ", nativeQuery = true)
    Optional<List<Story>> findStoriesByStoryUPK(StoryUPK storyUPK);

    @Query(value = "select * from story where product_id = #{#storyEdition.storyUPK.productId} and " +
            "story_id = #{#storyEdition.storyUPK.storyId} and edition = #{#storyEdition.storyUPK.edition}", nativeQuery = true)
    Story findStoryByEdition(StoryEdition storyEdition);
}
