package fcmb.com.good.services.rooms;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.roomsRequest.RoomTypeRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.roomsResponse.RoomTypeResponse;
import fcmb.com.good.model.entity.rooms.RoomCategory;
import fcmb.com.good.repo.rooms.RoomCategoryRepository;
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
public class RoomCategoryServiceImpl implements RoomCategoryService {
    private  final RoomCategoryRepository roomCategoryRepository;

    @Override
    public ApiResponse<List<RoomTypeResponse>> getListOfRoomType(int page, int size) {
        List<RoomCategory> roomTypeList = roomCategoryRepository.findAll(PageRequest.of(page,size)).toList();
        if(roomTypeList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(roomTypeList, RoomTypeResponse.class));
    }

    @Override
    public ApiResponse<String> addRoomType(@RequestBody RoomTypeRequest request) {

        Optional<RoomCategory> roomType = validateDuplicateRoomType(request.getCategory());
        if (!roomType.isEmpty()) {
            return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
                    "This Room Category Already Exist");
        }
        roomCategoryRepository.save(getRoomTypeFromRequest(request));

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Added successfully");
    }

    private RoomCategory getRoomTypeFromRequest(RoomTypeRequest request) {
        RoomCategory roomType = new RoomCategory();
        roomType.setCategory(request.getCategory());
        roomType.setDescription(request.getDescription());
        roomType.setCost(request.getCost());
        roomType.setStatus(request.getStatus());

        return roomType;
    }

    private Optional<RoomCategory> validateDuplicateRoomType(String category) {
        Optional<RoomCategory> existingRoomType = roomCategoryRepository.findByRoomType(category);
        System.out.println(existingRoomType);
        return existingRoomType;
    }


    @Override
    public ApiResponse<RoomTypeResponse> getRoomTypeById(@RequestParam("id") UUID roomTypeId) {
        Optional<RoomCategory> roomType = roomCategoryRepository.findByUuid(roomTypeId);

        if(roomType.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        RoomCategory rt = roomType.get();
        return new ApiResponse<RoomTypeResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(rt,RoomTypeResponse.class));

    }

    private RoomCategory validateRoomType(UUID uuid){
        Optional<RoomCategory> roomType = roomCategoryRepository.findByUuid(uuid);
        if(roomType.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return roomType.get();
    }

    @Override
    public ApiResponse<String> updateRoomType(UUID roomTypeId, @RequestBody RoomTypeRequest request) {
        RoomCategory roomType = validateRoomType(roomTypeId);
        roomType.setCategory(request.getCategory());
        roomType.setDescription(request.getDescription());
        roomType.setCost(request.getCost());
        roomType.setStatus(request.getStatus());

        roomCategoryRepository.save(roomType);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Updated Successfully");
    }


    @Override
    public ApiResponse<String> deleteRoomType(UUID roomTypeId) {
        RoomCategory roomType = validateRoomType(roomTypeId);
        roomCategoryRepository.delete(roomType);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
