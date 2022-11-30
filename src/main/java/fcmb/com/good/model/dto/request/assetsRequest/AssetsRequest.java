package fcmb.com.good.model.dto.request.assetsRequest;


import fcmb.com.good.model.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_CATEGORY_ID;
import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssetsRequest  {

    @NotNull(message = INVALID_CATEGORY_ID)
    @NotEmpty(message = INVALID_CATEGORY_ID)
    private String category_id;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String asset_name;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private Double value_cost;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String description;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private Long record_id;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String record_type;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String code;

    @NotNull(message = INVALID_NAME)
    @NotEmpty(message = INVALID_NAME)
    private String Location;

}
