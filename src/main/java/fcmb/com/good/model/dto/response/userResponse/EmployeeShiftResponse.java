package fcmb.com.good.model.dto.response.userResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class EmployeeShiftResponse extends BaseDto {

     Long employee_id;
     String designation;
     String shift;
     String period;

}
