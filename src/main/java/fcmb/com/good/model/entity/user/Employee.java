package fcmb.com.good.model.entity.user;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.BaseUser;
import fcmb.com.good.model.entity.products.ProductOrder;
import fcmb.com.good.model.entity.transaction.ExpenseRequest;
import fcmb.com.good.model.entity.transaction.MaintenanceRequest;
import fcmb.com.good.model.entity.services.ServiceRequest;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "employee")
public class Employee extends BaseUser {

    private String designation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "createdById", updatable = true)
    private AppUser createdBy;

    @OneToMany(mappedBy = "employee")
    private List<ExpenseRequest> expenseRequestList;

    @OneToMany(mappedBy = "employee")
    private List<ServiceRequest>  serviceRequestList;

    @OneToMany(mappedBy = "employee")
    private List<ProductOrder>  productOrderList;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeShift>  employeeShiftList;

    @OneToMany(mappedBy = "employee")
    private List<MaintenanceRequest>  maintenanceRequestList;


    public Employee(){}


    }
