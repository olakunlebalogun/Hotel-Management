package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import fcmb.com.good.model.entity.products.ProductCategory;
import lombok.Data;

@Data
public class ProductResponse extends BaseDto {

     private String name;
     private String description;
     private String quantity;
     private Double price;
     private String category;
     private String posted_by;
     private String code;
     private String location;


}
