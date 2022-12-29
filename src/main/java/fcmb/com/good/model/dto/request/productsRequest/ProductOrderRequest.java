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
    private Long customer_id;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private Long product_id;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private Double amount;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private Double tax;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String order_no;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String account_no;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private Double profit;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String sales_person;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String order_state;

}
