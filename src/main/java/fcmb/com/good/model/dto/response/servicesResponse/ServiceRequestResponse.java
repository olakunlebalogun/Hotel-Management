package fcmb.com.good.model.dto.response.servicesResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ServiceRequestResponse extends BaseDto {

     Long service_id;
     Long customer_id;
     String service_type;
     String serviced_by;

}
