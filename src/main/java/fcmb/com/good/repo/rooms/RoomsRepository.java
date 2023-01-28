package fcmb.com.good.repo.rooms;

import fcmb.com.good.model.entity.products.Product;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

    @Query("select st from Rooms st where st.uuid=:recordId")
    Optional<Rooms> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Rooms st where st.uuid=:recordId")
    Optional<Rooms> deleteByUuid(@Param("recordId")UUID uuid);

    Optional<Rooms> findByRoomNumber(Integer roomNumber);



}
