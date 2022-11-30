package fcmb.com.good.model.dto.request.othersRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_NAME;

@Data
public class DocumentRequest  {

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String file_type;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String file_name;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String file_size;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       String description;

       @NotNull(message = INVALID_NAME)
       @NotEmpty(message = INVALID_NAME)
       Long record_id;

}
