package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ExpenseRequest {

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      String expense_type;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      String description;

}
