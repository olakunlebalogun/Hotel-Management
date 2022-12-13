package fcmb.com.good.model.entity.products;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "products")
public class Products extends BaseEntity {

    private String name;
    private String description;
    private String quantity;
    private Double price;
    private String category;
    private String code;
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_Id", insertable = false, updatable = false)
    private Customer customer;


    public Products(){}


}
