package fcmb.com.good.model.entity.transaction;


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
@Table(name = "maintenanceRequest")
public class MaintenanceRequest extends BaseEntity {

    private String maintenance_category;
    private Long asset_id;
    private String comment;
    private String quantity;
    private Double cost;
    private String status;
    private String requested_by;
    private String maintained_by;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;


    public MaintenanceRequest(){}

    }
