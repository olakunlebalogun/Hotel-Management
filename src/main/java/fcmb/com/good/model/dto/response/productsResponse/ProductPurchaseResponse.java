package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductPurchaseResponse extends BaseDto {

     Long product_id;
     String description;
     String company_name;
     String quantity;
     Double price;


}
