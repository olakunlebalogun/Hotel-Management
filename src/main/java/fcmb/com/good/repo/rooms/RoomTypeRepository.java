package fcmb.com.good.repo.rooms;

import fcmb.com.good.model.entity.rooms.RoomType;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    @Query("select st from RoomType st where st.uuid=:recordId")
    Optional<RoomType> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from RoomType st where st.uuid=:recordId")
    Optional<RoomType> deleteByUuid(@Param("recordId")UUID uuid);

}
