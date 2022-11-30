package fcmb.com.good.services.rooms;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.roomsRequest.RoomTypeRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.roomsResponse.RoomTypeResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.rooms.RoomType;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.rooms.RoomTypeRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private  final RoomTypeRepository roomTypeRepository;

    @Override
    public ApiResponse<List<RoomTypeResponse>> getListOfRoomType(int page, int size) {
        List<RoomType> roomTypeList = roomTypeRepository.findAll(PageRequest.of(page,size)).toList();
        if(roomTypeList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(roomTypeList, RoomTypeResponse.class));
    }

    @Override
    public ApiResponse<RoomTypeResponse> addRoomType(@RequestBody RoomTypeRequest request) {
        RoomType roomType = Mapper.convertObject(request,RoomType.class);
        roomType=roomTypeRepository.save(roomType);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(roomType, RoomTypeResponse.class));

    }

    @Override
    public ApiResponse<RoomTypeResponse> getRoomTypeById(@RequestParam("id") UUID roomTypeId) {
        Optional<RoomType> roomType = roomTypeRepository.findByUuid(roomTypeId);

        if(roomType.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        RoomType rt = roomType.get();
        return new ApiResponse<RoomTypeResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(rt,RoomTypeResponse.class));

    }

    private RoomType validateRoomType(UUID uuid){
        Optional<RoomType> roomType = roomTypeRepository.findByUuid(uuid);
        if(roomType.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return roomType.get();
    }

    @Override
    public ApiResponse<RoomTypeResponse> updateRoomType(UUID roomTypeId, @RequestBody RoomTypeRequest request) {
        RoomType roomType = validateRoomType(roomTypeId);
        roomType.setRoom_type(request.getRoom_type());
        roomType.setDescription(request.getDescription());
        roomType.setCost(request.getCost());
        roomType.setStatus(request.getStatus());

        roomType = roomTypeRepository.save(roomType);
        return new ApiResponse<RoomTypeResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(roomType,RoomTypeResponse.class));
    }


    @Override
    public ApiResponse<String> deleteRoomType(UUID roomTypeId) {
        RoomType roomType = validateRoomType(roomTypeId);
        roomTypeRepository.delete(roomType);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
