package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductPurchaseResponse extends BaseDto {

     private Long product_id;
     private String description;
     private String company_name;
     private String quantity;
     private Double price;


}
