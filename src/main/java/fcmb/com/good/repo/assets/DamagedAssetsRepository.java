package fcmb.com.good.repo.assets;

import fcmb.com.good.model.entity.assets.DamagedAssets;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DamagedAssetsRepository extends JpaRepository<DamagedAssets,Long> {

    @Query("select st from DamagedAssets st where st.uuid=:recordId")
    Optional<DamagedAssets> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from DamagedAssets st where st.uuid=:recordId")
    Optional<DamagedAssets> deleteByUuid(@Param("recordId")UUID uuid);

}
