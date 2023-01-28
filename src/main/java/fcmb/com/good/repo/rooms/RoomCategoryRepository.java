package fcmb.com.good.repo.rooms;

import fcmb.com.good.model.entity.rooms.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomCategoryRepository extends JpaRepository<RoomCategory, Long> {

    @Query("select st from RoomCategory st where st.uuid=:recordId")
    Optional<RoomCategory> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from RoomCategory st where st.uuid=:recordId")
    Optional<RoomCategory> deleteByUuid(@Param("recordId")UUID uuid);

    @Query("select st from RoomCategory st where st.category=:category")
    Optional<RoomCategory> findByRoomType(@Param("category") String category);


}
