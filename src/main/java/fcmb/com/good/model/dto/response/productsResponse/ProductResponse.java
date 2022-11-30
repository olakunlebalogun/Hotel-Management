package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductResponse extends BaseDto {

     String name;
     String description;
     String quantity;
     Double price;
     String category;
     String posted_by;
     String code;
     String location;
}
