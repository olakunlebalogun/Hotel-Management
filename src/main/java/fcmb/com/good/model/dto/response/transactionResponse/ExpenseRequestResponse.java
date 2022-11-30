package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ExpenseRequestResponse extends BaseDto {

     Long expense_id;
     Long employee_id;
     String quantity;
     Double amount;
     Long account_no;
     String status;

}
