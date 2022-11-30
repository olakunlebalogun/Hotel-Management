package fcmb.com.good.model.dto.response.productsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ProductOrderResponse extends BaseDto {
     Long customer_id;
     Long product_id;
     Double amount;
     Double tax;
     Long order_no;
     Long account_no;
     Double profit;
     String sales_person;
     String order_state;
}
