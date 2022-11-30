package fcmb.com.good.model.dto.request.othersRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class HotelRequest  {

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String name;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String email;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String country;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String state;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String city;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String address;

        @NotNull(message = INVALID_NAME)
        @NotEmpty(message = INVALID_NAME)
        String phone;

}
