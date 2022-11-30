package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ExpenseRequestRequest  {

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Long expense_id;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Long employee_id;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String quantity;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Double amount;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String account_no;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String status;

}
