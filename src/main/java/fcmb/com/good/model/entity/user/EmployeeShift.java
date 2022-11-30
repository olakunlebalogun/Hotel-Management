package fcmb.com.good.model.entity.user;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@EntityListeners(BaseListener.class)
@Entity
@Table(name = "employeeShift")
public class EmployeeShift extends BaseEntity {

    private Long employee_id;
    private String designation;
    private String shift;
    private String period;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false )
    private Employee employee;


    public EmployeeShift(){}

   }
