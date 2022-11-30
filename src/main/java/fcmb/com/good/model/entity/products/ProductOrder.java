package fcmb.com.good.model.entity.products;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.user.Employee;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "productOrder")
public class ProductOrder extends BaseEntity {

    private Long customer_id;
    private Long product_id;
    private Double amount;
    private Double tax;
    private String order_no;
    private String account_no;
    private Double profit;
    private String sales_person;
    private String order_state;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id",insertable = false, updatable = false)
    private Employee employee;

    public ProductOrder(){}



}
