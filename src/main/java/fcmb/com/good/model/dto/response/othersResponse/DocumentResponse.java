package fcmb.com.good.model.dto.response.othersResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class DocumentResponse extends BaseDto {

     String file_type;
     String file_name;
     String file_size;
     String description;
     String record_id;

}
