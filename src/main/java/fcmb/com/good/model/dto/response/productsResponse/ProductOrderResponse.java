package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductOrderResponse extends BaseDto {
     private Long customer_id;
     private Long product_id;
     private Double amount;
     private Double tax;
     private Long order_no;
     private Long account_no;
     private Double profit;
     private String sales_person;
     private String order_state;
}
