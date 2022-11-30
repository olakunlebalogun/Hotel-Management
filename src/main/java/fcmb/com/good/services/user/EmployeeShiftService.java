package fcmb.com.good.services.user;

import fcmb.com.good.model.dto.request.userRequest.EmployeeShiftRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.EmployeeShiftResponse;
import fcmb.com.good.model.entity.user.EmployeeShift;

import java.util.List;
import java.util.UUID;

public interface EmployeeShiftService {


    ApiResponse<List<EmployeeShiftResponse>> getListOfEmployeeShift(int page, int size);

    ApiResponse<EmployeeShiftResponse> addEmployeeShift(EmployeeShiftRequest request);

    ApiResponse<EmployeeShiftResponse> getEmployeeShiftById(UUID employeeShiftId);

    ApiResponse<EmployeeShiftResponse> updateEmployeeShift(UUID employeeShiftId, EmployeeShiftRequest request);

    ApiResponse<EmployeeShiftResponse> deleteEmployeeShift(UUID employeeShiftId);




}
