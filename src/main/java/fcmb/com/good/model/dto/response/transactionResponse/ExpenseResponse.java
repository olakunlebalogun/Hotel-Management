package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ExpenseResponse extends BaseDto {

     private String expense_type;
     private String description;

}
