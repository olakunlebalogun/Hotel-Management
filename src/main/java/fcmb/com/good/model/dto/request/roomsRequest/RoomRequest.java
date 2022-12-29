package fcmb.com.good.model.dto.request.roomsRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class RoomRequest  {

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String room_type;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String room_no;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String room_description;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private Double price;

//      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private String room_status;

//      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private String available_rooms;

//      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private String state;

//      @NotNull(message = INVALID_NAME)
//      @NotEmpty(message = INVALID_NAME)
      private String current_customer;


}
