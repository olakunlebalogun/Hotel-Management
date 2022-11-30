package fcmb.com.good.model.dto.response.roomsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class RoomResponse extends BaseDto {

     String room_type;
     Long room_no;
     String room_description;
     Double price;
     String room_status;
     String available_rooms;
     String state;
     String current_customer;
}
