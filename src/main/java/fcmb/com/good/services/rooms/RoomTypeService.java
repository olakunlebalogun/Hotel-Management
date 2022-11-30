package fcmb.com.good.services.rooms;

import fcmb.com.good.model.dto.request.roomsRequest.RoomTypeRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.roomsResponse.RoomTypeResponse;
import fcmb.com.good.model.entity.rooms.RoomType;

import java.util.List;
import java.util.UUID;

public interface RoomTypeService {

    ApiResponse<List<RoomTypeResponse>> getListOfRoomType(int page, int size);

    ApiResponse<RoomTypeResponse> addRoomType(RoomTypeRequest request);

    ApiResponse<RoomTypeResponse> getRoomTypeById(UUID roomTypeId);

    ApiResponse<RoomTypeResponse> updateRoomType( UUID roomTypeId, RoomTypeRequest request);

    ApiResponse<String> deleteRoomType(UUID roomTypeId);




}
