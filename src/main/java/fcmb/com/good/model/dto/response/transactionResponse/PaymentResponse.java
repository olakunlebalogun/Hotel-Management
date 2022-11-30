package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentResponse extends BaseDto {

     Long customer_id;
     String payment_type;
     Double amount;
     String payment_status;
     String payment_details;

}
