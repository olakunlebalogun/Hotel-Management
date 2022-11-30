package fcmb.com.good.model.dto.response.userResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class EmployeeResponse extends BaseDto {

     String name;
     String email;
     String gender;
     String photo;
     String country;
     String city;
     String address;
     String phone;
     String designation;
     String username;

}
