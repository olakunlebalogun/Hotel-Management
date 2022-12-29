package fcmb.com.good.model.dto.response.assetsResponse;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class DamagedAssetsResponse extends BaseDto {

     private Long asset_id;
     private Long asset_category_id;
     private String quantity;
     private String status;
     private String comment;

}
