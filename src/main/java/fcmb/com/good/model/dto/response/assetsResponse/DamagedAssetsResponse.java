package fcmb.com.good.model.dto.response.assetsResponse;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class DamagedAssetsResponse extends BaseDto {

     Long asset_id;
     Long asset_category_id;
     String quantity;
     String status;
     String comment;

}
