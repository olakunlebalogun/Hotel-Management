package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class BookingRequest  {

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      Long customer_id;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      Long room_id;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      Double price;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      String quantity;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      Date check_in_date;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      Date check_out_date;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      String night;

}
