package fscut.manager.demo.dao;

import fscut.manager.demo.entity.Story;
import fscut.manager.demo.entity.StoryEdition;
import fscut.manager.demo.entity.UPK.StoryUPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface StoryRepository extends JpaRepository<Story, StoryUPK> {

    @Query(value = "select story_id from story_edition where product_id = ?1 order by story_id DESC limit 1", nativeQuery = true)
    Integer findLastedStoryId(@Param("productId") Integer productId);


    @Modifying
    @Query(value = "delete from story_edition where product_id = :#{#storyUPK.productId} and story_id = :#{#storyUPK.storyId}", nativeQuery = true)
    Integer deleteEditionByStoryUPK(@Param("storyUPK") StoryUPK storyUPK);


    @Query(value = "select * from story where product_id = :#{#storyUPK.productId} and story_id = :#{#storyUPK.storyId} order by edition desc ", nativeQuery = true)
    List<Story> findStoriesByStoryUPK(@Param("storyUPK") StoryUPK storyUPK);

    @Query(value = "select * from story where product_id = :#{#storyUPK.productId} and " +
            "story_id = :#{#storyUPK.storyId} and edition = :#{#storyUPK.edition}", nativeQuery = true)
    Story findStoryByEdition(@Param("storyUPK") StoryUPK storyUPK);

    @Modifying
    @Query(value = "delete from story where product_id = :#{#storyUPK.productId} and story_id = :#{#storyUPK.storyId}", nativeQuery = true)
    void deleteStories(@Param("storyUPK") StoryUPK storyUPK);

    Page<Story> findByNameContains(String name, Pageable pageable);

}
