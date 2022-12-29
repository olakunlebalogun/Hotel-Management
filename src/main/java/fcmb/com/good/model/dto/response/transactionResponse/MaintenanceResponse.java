package fcmb.com.good.model.dto.response.transactionResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class MaintenanceResponse extends BaseDto {

     private String maintenance_category;
     private Long asset_id;
     private String comment;
     private String quantity;
     private Long cost;
     private String status;
     private String requested_by;
     private String maintained_by;

}
