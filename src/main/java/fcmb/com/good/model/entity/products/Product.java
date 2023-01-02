package fcmb.com.good.model.entity.products;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.user.AppUser;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

//@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private Double purchasedPrice;
    private String category;
    private String code;
    private String location;
    private String status;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_Id", insertable = false, updatable = false)
    private AppUser createdBy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id", insertable = false, updatable = false)
    private ProductCategory productCategory;

    public Product(){}


}
