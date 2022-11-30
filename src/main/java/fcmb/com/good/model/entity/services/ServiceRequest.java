package fcmb.com.good.model.entity.services;

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
@Table(name = "serviceRequest")
public class ServiceRequest extends BaseEntity {

    private Long service_id;
    private Long customer_id;
    private String service_type;
    private String serviced_by;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    public ServiceRequest(){}


   }
