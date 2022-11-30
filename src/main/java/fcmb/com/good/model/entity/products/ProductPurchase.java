package fcmb.com.good.model.entity.products;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "productPurchase")
public class ProductPurchase extends BaseEntity {

    private Long product_id;
    private String description;
    private String company_name;
    private String quantity;
    private Double price;

    public ProductPurchase(){}

}
