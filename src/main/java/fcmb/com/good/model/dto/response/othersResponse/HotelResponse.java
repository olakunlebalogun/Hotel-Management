package fcmb.com.good.model.dto.response.othersResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class HotelResponse extends BaseDto {

     String name;
     String email;
     String country;
     String state;
     String city;
     String address;
     String phone;

}
