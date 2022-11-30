package fcmb.com.good.model.dto.request.productsRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ProductRequest  {

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String name;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String description;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String quantity;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String price;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String category;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String posted_by;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String code;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String location;

}
