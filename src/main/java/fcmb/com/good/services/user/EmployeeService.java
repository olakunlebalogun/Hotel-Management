package fcmb.com.good.services.user;

import fcmb.com.good.model.dto.request.userRequest.EmployeeRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {


   ApiResponse<List<EmployeeResponse>> getListOfEmployee(int page, int size);

    ApiResponse<EmployeeResponse> addEmployee(EmployeeRequest request);

    ApiResponse<EmployeeResponse> getEmployeeById(UUID employeeId);

    ApiResponse<EmployeeResponse> updateEmployee(UUID employeeId, EmployeeRequest employee);

    ApiResponse<String> deleteEmployee(UUID employeeId);




}
