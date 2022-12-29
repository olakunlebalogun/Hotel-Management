package fcmb.com.good.model.dto.request.productsRequest;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.PRODUCT_NAME;

@Data
public class ProductCategoryRequest {

//    @NotNull(message = PRODUCT_NAME)
//    @NotEmpty(message = PRODUCT_NAME)
    private String name;

}
