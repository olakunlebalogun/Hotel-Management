package fcmb.com.good.model.dto.response.servicesResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class ServiceResponse extends BaseDto {

     String service_type;
     String description;
     String service_category;

}
