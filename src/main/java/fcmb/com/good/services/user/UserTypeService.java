package fcmb.com.good.services.user;

import fcmb.com.good.model.dto.request.userRequest.UserTypeRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.UserTypeResponse;

import java.util.List;
import java.util.UUID;

public interface UserTypeService {

    ApiResponse<List<UserTypeResponse>> getListOfUserType(int page, int size);

    ApiResponse<UserTypeResponse> addUserType(UserTypeRequest request);

    ApiResponse<UserTypeResponse> getUserTypeById(UUID userTypeId);

    ApiResponse<UserTypeResponse> updateUserType(UUID userTypeId, UserTypeRequest request);

    ApiResponse<String> deleteUserType(UUID usertypeId);

}
