package fcmb.com.good.model.dto.response.servicesResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ServiceRequestResponse extends BaseDto {

     private Long service_id;
     private Long customer_id;
     private String service_type;
     private String serviced_by;

}
