package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class BookingResponse extends BaseDto {

     Long customer_id;
     Long room_id;
     Double price;
     String quantity;
     Date check_in_date;
     Date check_out_date;
     String night;

}
