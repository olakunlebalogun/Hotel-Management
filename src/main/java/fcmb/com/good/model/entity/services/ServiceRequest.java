package fcmb.com.good.model.entity.services;

import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.products.Products;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.entity.user.Employee;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "serviceRequest")
public class ServiceRequest extends BaseEntity {

    private Long service_id;
    private Long customer_id;
    private String service_type;
    private String serviced_by;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "service_Id", insertable = false, updatable = false)
    private Services services;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;


    public ServiceRequest(){}


   }
