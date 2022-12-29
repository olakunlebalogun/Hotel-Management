package fcmb.com.good.model.dto.response.roomsResponse;


import fcmb.com.good.model.dto.BaseDto;
import lombok.Data;

@Data
public class RoomResponse extends BaseDto {

     private String room_type;
     private Long room_no;
     private String room_description;
     private Double price;
     private String room_status;
     private String available_rooms;
     private String state;
     private String current_customer;
}
