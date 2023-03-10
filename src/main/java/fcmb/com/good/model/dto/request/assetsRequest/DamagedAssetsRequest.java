package fcmb.com.good.model.dto.request.assetsRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class DamagedAssetsRequest  {

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     private Long asset_id;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     private Long asset_category_id;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     private String quantity;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     private String status;

     @NotNull(message = INVALID_NAME)
     @NotEmpty(message = INVALID_NAME)
     private String comment;

}
