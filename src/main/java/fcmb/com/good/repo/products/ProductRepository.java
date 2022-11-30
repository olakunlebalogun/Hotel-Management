package fcmb.com.good.repo.products;

import fcmb.com.good.model.entity.products.Products;
import fcmb.com.good.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    @Query("select st from Products st where st.uuid=:recordId")
    Optional<Products> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Products st where st.uuid=:recordId")
    Optional<Products> deleteByUuid(@Param("recordId")UUID uuid);

}
