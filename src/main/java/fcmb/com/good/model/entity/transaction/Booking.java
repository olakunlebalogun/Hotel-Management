package fcmb.com.good.model.entity.transaction;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@EntityListeners(BaseListener.class)
@Table(name = "booking")
public class Booking extends BaseEntity {

    private Long customer_id;
    private Long room_id;
    private Double price;
    private String quantity;
    private Date check_in_date;
    private Date check_out_date;
    private String night;

    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;


    public Booking(){

    }

    }
