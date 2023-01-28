package fcmb.com.good.model.dto.request.transactionRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class BookingRequest  {

      @NotNull(message = INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
      private UUID customer_id;

      @NotNull(message = INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
      private UUID room_id;

      @NotNull(message = INVALID_NAME)
//    @NotEmpty(message = INVALID_NAME)
      private UUID roomCategoryId;

      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private Double price;

      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private Date check_in_date;

      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private Date check_out_date;

      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private Integer night;

//      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
//      private Double amount;

}
