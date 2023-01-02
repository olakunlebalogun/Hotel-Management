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
@Table(name = "productOrderItems")
public class ProductOrderItems extends BaseEntity {

    private Long product_id;
    private Long product_order_id;
    private String product_name;
    private String quantity;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productOrders_Id", insertable = false, updatable = false)
    private ProductOrder productOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_Id", insertable = false, updatable = false)
    private Product product;

    public ProductOrderItems(){}

}
