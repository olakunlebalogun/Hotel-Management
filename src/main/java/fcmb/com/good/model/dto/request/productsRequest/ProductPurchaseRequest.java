package fcmb.com.good.model.dto.request.productsRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ProductPurchaseRequest  {

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Long product_id;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String description;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String company_name;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     String quantity;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     Double price;

}
