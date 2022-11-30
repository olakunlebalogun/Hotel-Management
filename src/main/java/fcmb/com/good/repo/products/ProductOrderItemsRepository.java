package fcmb.com.good.repo.products;

import fcmb.com.good.model.entity.products.ProductOrderItems;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductOrderItemsRepository extends JpaRepository<ProductOrderItems, Long> {

    @Query("select st from ProductOrderItems st where st.uuid=:recordId")
    Optional<ProductOrderItems> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from ProductOrderItems st where st.uuid=:recordId")
    Optional<ProductOrderItems> deleteByUuid(@Param("recordId")UUID uuid);

}
