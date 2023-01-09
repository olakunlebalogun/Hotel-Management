package fcmb.com.good.model.dto.request.roomsRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.UUID;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class RoomRequest  {

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private UUID category;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private Integer roomNumber;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String description;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private Double price;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String status;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String availableRooms;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String state;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String currentCustomer;

      @NotNull(message = INVALID_NAME)
      @NotEmpty(message = INVALID_NAME)
      private String createdBy;

}
