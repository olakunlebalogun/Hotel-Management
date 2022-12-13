package fcmb.com.good.services.rooms;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.roomsRequest.RoomRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.roomsResponse.RoomResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.rooms.RoomsRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private  final RoomsRepository roomRepository;

    @Override
    public ApiResponse<List<RoomResponse>> getListOfRoom(int page, int size) {
        List<Rooms> roomsList = roomRepository.findAll(PageRequest.of(page,size)).toList();
        if(roomsList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(roomsList, RoomResponse.class));
    }

    @Override
    public ApiResponse<RoomResponse> addRoom(@RequestBody RoomRequest request) {
        Rooms rooms = Mapper.convertObject(request,Rooms.class);
        rooms=roomRepository.save(rooms);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(rooms, RoomResponse.class));
    }

    @Override
    public ApiResponse<RoomResponse> getRoomById(@RequestParam("id") UUID roomId) {
        Optional<Rooms> rooms = roomRepository.findByUuid(roomId);

        if(rooms.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        Rooms cm = rooms.get();
        return new ApiResponse<RoomResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(cm,RoomResponse.class));

    }

    private Rooms validateRooms(UUID uuid){
        Optional<Rooms> rooms = roomRepository.findByUuid(uuid);
        if(rooms.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return rooms.get();
    }

    @Override
    public ApiResponse<RoomResponse> updateRoom(UUID roomId, @RequestBody RoomRequest request) {
        Rooms rooms = validateRooms(roomId);
        rooms.setRoom_type(request.getRoom_type());
        rooms.setRoom_no(request.getRoom_no());
        rooms.setRoom_description(request.getRoom_description());
        rooms.setPrice(request.getPrice());
        rooms.setRoom_status(request.getRoom_status());
        //rooms.setAvailable_rooms(request.getAvailable_rooms());
        rooms.setState(request.getState());
        rooms.setCurrent_customer(request.getCurrent_customer());

        rooms = roomRepository.save(rooms);
        return new ApiResponse<RoomResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(rooms,RoomResponse.class));
    }

    @Override
    public ApiResponse<String> deleteRoom(UUID roomId) {
        Rooms rooms = validateRooms(roomId);
        roomRepository.delete(rooms);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
