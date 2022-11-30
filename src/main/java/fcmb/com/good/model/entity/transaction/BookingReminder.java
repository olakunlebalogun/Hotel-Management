package fcmb.com.good.model.entity.transaction;


import fcmb.com.good.model.entity.BaseEntity;
import fcmb.com.good.model.listener.BaseListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

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


    public BookingReminder(){

    }

   }
