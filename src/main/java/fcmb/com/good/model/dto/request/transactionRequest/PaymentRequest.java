package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class PaymentRequest  {

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       Long customer_id;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String payment_type;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       Double amount;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String payment_status;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String payment_details;

}
