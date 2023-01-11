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


    /**
     * @Finding the list of rooms*
     * @Validate the List of rooms is empty otherwise return record not found*
     * @return the list of rooms and a Success Message* *
     * * */
    @Override
    public ApiResponse<List<RoomResponse>> getListOfRoom(int page, int size) {
        List<Rooms> roomsList = roomsRepository.findAll(PageRequest.of(page,size)).toList();
        if(roomsList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(roomsList, RoomResponse.class));
    }

    /**
     * @Validating existingRoomsOptional by roomNumber*
     * @Validate the List of existingRoomsOptional is empty otherwise return Duplicate Record*
     * * */
    private void validateDuplicationRooms(Integer roomNumber){
        Optional<Rooms> existingRoomsOptional = roomsRepository.findByRoomNumber(roomNumber);
        if(existingRoomsOptional.isPresent())
            throw new RecordNotFoundException("Duplicate record");
    }


    @Override
    /**
     * @Validate that no duplicate roomNumber is allowed*
     * @Validate that room category exists otherwise return record not found*
     * @Validate that user creating the room exists, otherwise return user not found*
     * Create the room definition and save
     * @return success message* *
     * * */
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
        rooms.setStatus(request.getStatus());
        rooms.setCreatedBy(String.valueOf(existingUser));

        rooms.setRoomCategory(existingRoomCategory);
        roomsRepository.save(rooms);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");
    }

    @Override
    /**
     * @Finding the list of all roomsOptional by uuid*
     * @Validate if the List of roomsOptional is empty otherwise return record not found
     * Create the room definition and get the room Optional by uuid
     * @return the list of rooms and a Success Message* *
     * * */
    public ApiResponse<RoomResponse> getRoomById(@RequestParam("id") UUID roomId) {
        Optional<Rooms> roomsOptional = roomsRepository.findByUuid(roomId);

        if(roomsOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        Rooms rooms = roomsOptional.get();
        return new ApiResponse<RoomResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(rooms,RoomResponse.class));

    }

    /**
     * @validating rooms by uuid*
     * @Validate if the List of rooms is empty otherwise return record not found
     * @return the list of rooms* *
     * * */
    private Rooms validateRooms(UUID uuid){
        Optional<Rooms> rooms = roomsRepository.findByUuid(uuid);
        if(rooms.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return rooms.get();
    }

    @Override
    /**
     * @Validating the list of existingRoomCategory by uuid*
     * @Validate if the List of existingRoomCategory is empty otherwise return record not found
     * Create the room definition and save
     * @return a Success Message* *
     * * */
    public ApiResponse<String> updateRoom(UUID roomId, @RequestBody RoomRequest request) {

        RoomCategory existingRoomCategory = roomCategoryRepository.findByUuid(request.getCategory())
                .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Rooms rooms = validateRooms(roomId);
        rooms.setRoomNumber(request.getRoomNumber());
        rooms.setDescription(request.getDescription());
        rooms.setPrice(request.getPrice());
        rooms.setCategory(String.valueOf(request.getCategory()));
        rooms.setCategory(existingRoomCategory.getUuid().toString());
        rooms.setState(request.getState());
        rooms.setStatus(request.getStatus());

        rooms = roomsRepository.save(rooms);
        return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record updated successfully");
    }

    @Override
    /**
     * @validating rooms by uuid*
     * @Validate if rooms is empty otherwise return record not found
     * @Delete room
     * @return a Success Message* *
     * * */
    public ApiResponse<String> deleteRoom(UUID roomId) {
        Rooms rooms = validateRooms(roomId);
        roomsRepository.delete(rooms);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
