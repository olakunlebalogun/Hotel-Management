package fcmb.com.good.model.dto.response.roomsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class RoomTypeResponse extends BaseDto {

     private String room_type;
     private String description;
     private Double cost;
     private String status;

}
