package fcmb.com.good.services.rooms;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.roomsRequest.RoomRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.roomsResponse.RoomResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.products.Product;
import fcmb.com.good.model.entity.products.ProductCategory;
import fcmb.com.good.model.entity.rooms.RoomCategory;
import fcmb.com.good.model.entity.rooms.Rooms;
import fcmb.com.good.model.entity.user.AppUser;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.rooms.RoomCategoryRepository;
import fcmb.com.good.repo.rooms.RoomsRepository;
import fcmb.com.good.repo.user.UserRepository;
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
    private  final RoomsRepository roomsRepository;
    private  final RoomCategoryRepository roomCategoryRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<List<RoomResponse>> getListOfRoom(int page, int size) {
        List<Rooms> roomsList = roomsRepository.findAll(PageRequest.of(page,size)).toList();
        if(roomsList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(roomsList, RoomResponse.class));
    }


    private void validateDuplicationRooms(Integer roomNumber){
        Optional<Rooms> existingRoomsOptional = roomsRepository.findByRoomNumber(roomNumber);
        if(existingRoomsOptional.isPresent())
            throw new RecordNotFoundException("Duplicate record");
    }


    @Override
    public ApiResponse<String> addRoom(@RequestBody RoomRequest request) {

        validateDuplicationRooms(request.getRoomNumber());

        RoomCategory existingRoomCategory = roomCategoryRepository.findByUuid(request.getCategory())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        AppUser existingUser  = userRepository.findByUuid(UUID.fromString(request.getCreatedBy()))
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Rooms rooms = new Rooms();
        rooms.setDescription(request.getDescription());
        rooms.setRoomNumber(request.getRoomNumber());
        rooms.setPrice(request.getPrice());
        rooms.setCategory(existingRoomCategory.getUuid().toString());
        rooms.setStatus(request.getStatus());
        rooms.setState(request.getState());
        rooms.setCurrentCustomer(request.getCurrentCustomer());
        rooms.setRoomCategory(existingRoomCategory);
        rooms.setStatus(request.getStatus());
        rooms.setCreatedBy(String.valueOf(existingUser));
        roomsRepository.save(rooms);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");
    }

    @Override
    public ApiResponse<RoomResponse> getRoomById(@RequestParam("id") UUID roomId) {
        Optional<Rooms> rooms = roomsRepository.findByUuid(roomId);

        if(rooms.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        Rooms cm = rooms.get();
        return new ApiResponse<RoomResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(cm,RoomResponse.class));

    }

    private Rooms validateRooms(UUID uuid){
        Optional<Rooms> rooms = roomsRepository.findByUuid(uuid);
        if(rooms.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return rooms.get();
    }

    @Override
    public ApiResponse<String> updateRoom(UUID roomId, @RequestBody RoomRequest request) {
        Rooms rooms = validateRooms(roomId);
        rooms.setCategory(request.getCategory().toString());
        rooms.setRoomNumber(request.getRoomNumber());
        rooms.setDescription(request.getDescription());
        rooms.setPrice(request.getPrice());
        rooms.setStatus(request.getStatus());
        rooms.setState(request.getState());
        rooms.setCurrentCustomer(request.getCurrentCustomer());

        rooms = roomsRepository.save(rooms);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Updated Successfully");
    }

    @Override
    public ApiResponse<String> deleteRoom(UUID roomId) {
        Rooms rooms = validateRooms(roomId);
        roomsRepository.delete(rooms);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
