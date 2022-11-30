package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class AccountChartResponse extends BaseDto {

     Long category_id;
     String code;
     String account_name;
     Double balance;

}
