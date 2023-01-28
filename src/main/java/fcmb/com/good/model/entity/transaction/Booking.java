package fcmb.com.good.model.entity.transaction;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.rooms.RoomCategory;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@EntityListeners(BaseListener.class)
@Table(name = "booking")
public class Booking extends BaseEntity {

    private Double price;
    private Date check_in_date;
    private Date check_out_date;
    private Integer night;
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", insertable = true )
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId", insertable = true)
    private Rooms rooms;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roomCategoryId", insertable = true)
    private RoomCategory roomCategory;

    public Booking(){

    }

    }
