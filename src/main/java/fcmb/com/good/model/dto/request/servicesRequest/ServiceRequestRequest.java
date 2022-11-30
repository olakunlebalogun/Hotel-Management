package fcmb.com.good.model.dto.request.servicesRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class ServiceRequestRequest  {

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       Long service_id;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       Long customer_id;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String service_type;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String serviced_by;

}
