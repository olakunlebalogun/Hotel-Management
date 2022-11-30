package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class AccountChartRequest {

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Long category_id;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String code;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String account_name;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Double balance;

}
