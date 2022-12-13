package fcmb.com.good.model.entity.transaction;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.entity.products.Products;
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
@Table(name = "bookingReminder")
public class BookingReminder extends BaseEntity {

    private Long customer_id;
    private Long booking_id;
    private String reminder_details;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", insertable = false, updatable = false)
    private Booking booking;



    public BookingReminder(){

    }

   }
