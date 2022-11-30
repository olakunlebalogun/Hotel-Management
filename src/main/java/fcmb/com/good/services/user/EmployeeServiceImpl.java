package fcmb.com.good.services.user;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.userRequest.EmployeeRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.dto.response.userResponse.EmployeeResponse;
import fcmb.com.good.model.entity.user.Employee;
import fcmb.com.good.repo.user.EmployeeRepository;
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
public class EmployeeServiceImpl implements EmployeeService {

    private  final EmployeeRepository employeeRepository;


    @Override
    public ApiResponse<List<EmployeeResponse>> getListOfEmployee(int page, int size) {
        List<Employee> employeeList = employeeRepository.findAll(PageRequest.of(page,size)).toList();

        if(employeeList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                HttpStatus.OK.value(),
                Mapper.convertList(employeeList, EmployeeResponse.class));

    }


    @Override
    public ApiResponse<EmployeeResponse> addEmployee(@RequestBody EmployeeRequest request) {
        Employee employee = Mapper.convertObject(request,Employee.class);
        employee=employeeRepository.save(employee);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(employee,EmployeeResponse.class));

    }


    @Override
    public ApiResponse<EmployeeResponse> getEmployeeById(@RequestParam("id") UUID employeeId) {
        Optional<Employee> employee = employeeRepository.findByUuid(employeeId);

        if(employee.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        Employee cm = employee.get();
        return new ApiResponse<EmployeeResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(cm,EmployeeResponse.class));

    }


    private Employee validateEmployee(UUID uuid){
        Optional<Employee> employee = employeeRepository.findByUuid(uuid);

        if(employee.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return employee.get();
    }


    @Override
    public ApiResponse<EmployeeResponse> updateEmployee(UUID employeeId, @RequestBody EmployeeRequest employee) {
        Employee employ = validateEmployee(employeeId);
        employ.setName(employee.getName());
        employ.setEmail(employee.getEmail());
        employ.setGender(employee.getGender());
        employ.setCountry(employee.getCountry());
        employ.setCity(employee.getCity());
        employ.setAddress(employee.getAddress());
        employ.setPhone(employee.getPhone());
        employ.setDesignation(employee.getDesignation());
        employ.setUsername(employee.getUsername());

        employ = employeeRepository.save(employ);
        return new ApiResponse<EmployeeResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(employ,EmployeeResponse.class));

    }


    @Override
    public ApiResponse<String> deleteEmployee(UUID employeeId) {
        Employee employee = validateEmployee(employeeId);
        employeeRepository.delete(employee);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");

    }


}
