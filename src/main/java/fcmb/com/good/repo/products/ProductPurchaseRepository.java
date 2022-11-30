package fcmb.com.good.repo.products;

import fcmb.com.good.model.entity.products.ProductPurchase;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductPurchaseRepository extends JpaRepository<ProductPurchase, Long> {

    @Query("select st from ProductPurchase st where st.uuid=:recordId")
    Optional<ProductPurchase> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from ProductPurchase st where st.uuid=:recordId")
    Optional<ProductPurchase> deleteByUuid(@Param("recordId")UUID uuid);


}
