package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class BookingResponse extends BaseDto {

     private Long customer_id;
     private Long room_id;
     private Double price;
     private String quantity;
     private Date check_in_date;
     private Date check_out_date;
     private String night;

}
