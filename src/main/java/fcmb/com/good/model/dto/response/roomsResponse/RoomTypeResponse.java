package fcmb.com.good.model.dto.response.roomsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class RoomTypeResponse extends BaseDto {

     String room_type;
     String description;
     Double cost;
     String status;

}
