package fcmb.com.good.services.user;

import fcmb.com.good.model.dto.request.userRequest.EmployeeRequest;
import fcmb.com.good.model.dto.request.userRequest.loginEmployeeRequest;
import fcmb.com.good.model.dto.request.userRequest.loginUserRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {


   ApiResponse<List<EmployeeResponse>> getListOfEmployee(int page, int size);

    ApiResponse<String> addEmployee(EmployeeRequest request);

    ApiResponse<EmployeeResponse> getEmployeeById(UUID employeeId);

    ApiResponse<String> updateEmployee(UUID employeeId, EmployeeRequest employee);

    ApiResponse<String> deleteEmployee(UUID employeeId);

    ApiResponse<String> loginEmployee(String email, loginEmployeeRequest request);




}
