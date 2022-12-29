package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentResponse extends BaseDto {

     private Long customer_id;
     private String payment_type;
     private Double amount;
     private String payment_status;
     private String payment_details;

}
