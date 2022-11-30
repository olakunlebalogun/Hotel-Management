package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class MaintenanceResponse extends BaseDto {

     String maintenance_category;
     Long asset_id;
     String comment;
     String quantity;
     Long cost;
     String status;
     String requested_by;
     String maintained_by;

}
