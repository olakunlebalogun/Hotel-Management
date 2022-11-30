package fcmb.com.good.model.dto.request.productsRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ProductOrderRequest  {

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    Long customer_id;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    Long product_id;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    Double amount;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    Double tax;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    String order_no;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    String account_no;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    Double profit;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    String sales_person;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    String order_state;

}
