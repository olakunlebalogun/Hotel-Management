package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductOrderItemsResponse extends BaseDto {

     Long product_id;
     Long product_order_id;
     String product_name;
     String quantity;
     Double price;

}
