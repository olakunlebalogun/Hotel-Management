package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class BookingReminderResponse extends BaseDto {

     Long customer_id;
     Long booking_id;
     String reminder_details;
     String status;

}
