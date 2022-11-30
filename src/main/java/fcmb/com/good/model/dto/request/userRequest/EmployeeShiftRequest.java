package fcmb.com.good.model.dto.request.userRequest;

import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.*;

@Data
public class EmployeeShiftRequest {


    Long employee_id;

    @NotNull(message = INVALID_DESIGNATION)
    @NotEmpty(message = INVALID_DESIGNATION)
    String designation;

    @NotNull(message = INVALID_SHIFT)
    @NotEmpty(message = INVALID_SHIFT)
    String shift;

    @NotNull(message = INVALID_PERIOD)
    @NotEmpty(message = INVALID_PERIOD)
    String period;

}
