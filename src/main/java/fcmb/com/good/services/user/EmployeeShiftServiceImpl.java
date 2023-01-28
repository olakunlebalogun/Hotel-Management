package fcmb.com.good.services.user;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.userRequest.EmployeeShiftRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.dto.response.userResponse.EmployeeShiftResponse;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.model.entity.user.EmployeeShift;
import fcmb.com.good.repo.user.EmployeeShiftRepository;
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
public class EmployeeShiftServiceImpl implements EmployeeShiftService {

    private  final EmployeeShiftRepository employeeShiftRepository;


    @Override
    public ApiResponse<List<EmployeeShiftResponse>> getListOfEmployeeShift(int page, int size) {
        List<EmployeeShift> employeeShiftList = employeeShiftRepository.findAll(PageRequest.of(page,size)).toList();

        if(employeeShiftList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label,
                HttpStatus.OK.value(),
                Mapper.convertList(employeeShiftList, EmployeeShiftResponse.class));

    }


    @Override
    public ApiResponse<String> addEmployeeShift(@RequestBody EmployeeShiftRequest request) {
        EmployeeShift employeeShift = Mapper.convertObject(request,EmployeeShift.class);
        employeeShift = employeeShiftRepository.save(employeeShift);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record added Successfully");
    }


    @Override
    public  ApiResponse<EmployeeShiftResponse> getEmployeeShiftById(@RequestParam("id") UUID employeeShiftId) {
        Optional<EmployeeShift> employeeShift = employeeShiftRepository.findByUuid(employeeShiftId);

        if(employeeShift.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        EmployeeShift es = employeeShift.get();
        return new ApiResponse<EmployeeShiftResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(es,EmployeeShiftResponse.class));

    }


    private EmployeeShift validateEmployeeShift(UUID uuid){
        Optional<EmployeeShift> employeeShift = employeeShiftRepository.findByUuid(uuid);

        if(employeeShift.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return employeeShift.get();
    }

    @Override
    public ApiResponse<String> updateEmployeeShift(UUID employeeShiftId, @RequestBody EmployeeShiftRequest request) {
        EmployeeShift employeeShifty = validateEmployeeShift(employeeShiftId);
        employeeShifty.setDesignation(request.getDesignation());
        employeeShifty.setShift(request.getShift());
        employeeShifty.setPeriod(request.getPeriod());

        employeeShifty = employeeShiftRepository.save(employeeShifty);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Update Successfully");
    }


    @Override
    public  ApiResponse<String> deleteEmployeeShift(UUID employeeShiftId) {
        EmployeeShift employeeShift = validateEmployeeShift(employeeShiftId);
        employeeShiftRepository.delete(employeeShift);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
