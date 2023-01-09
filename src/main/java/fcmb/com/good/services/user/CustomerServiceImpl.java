package fcmb.com.good.services.user;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.filter.JwtFilter;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.request.userRequest.CustomerRequest;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.userRequest.changeCustomerPasswordRequest;
import fcmb.com.good.model.dto.request.userRequest.loginCustomerRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.user.AppUser;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.utills.EmailUtils;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService  {

    private  final CustomerRepository customerRepository;
    private final EmailUtils emailUtils;
    private final JwtFilter jwtFilter;

    @Value("${FILE_UPLOAD_LOCATION}")
    private String uploadLocation;

    @Override
    public ApiResponse<List<CustomerResponse>> getListOfCustomer(int page, int size) {
//        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()){
            List<Customer> customerList = customerRepository.findAll(PageRequest.of(page,size)).toList();
            if(customerList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertList(customerList, CustomerResponse.class));
        }
//        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
//    }

    @Override
    public ApiResponse<String> addCustomer(@RequestBody CustomerRequest request) {
        Optional<Customer> customer  = validateCustomerByEmailId(request.getEmail());
            if(!customer.isEmpty()){
                return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
                        "Email Already Exist");
            }
            customerRepository.save(getCustomerFromRequest(request));
            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record added Successfully");
        }



    @Override
    public ApiResponse<CustomerResponse> getCustomerById(UUID customerId) {
        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()){
            Optional<Customer> customer = customerRepository.findByUuid(customerId);
            if(customer.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            Customer cm = customer.get();
            return new ApiResponse<CustomerResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(cm,CustomerResponse.class));
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    private Optional<Customer> validateCustomerByEmailId(String email){
        Optional<Customer> customer = customerRepository.findByEmailId(email);
        return customer;
    }

    private Customer validateCustomer(UUID uuid){
    Optional<Customer> customer = customerRepository.findByUuid(uuid);
    if(customer.isEmpty())
        throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return customer.get();
    }

    private Customer getCustomerFromRequest(CustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setCountry(request.getCountry());
        customer.setCity(request.getCity());
        customer.setGender(request.getGender());
        customer.setPassword(request.getPassword());
        customer.setPhone(request.getPhone());
        customer.setUsername(request.getUsername());
        customer.setPhoto(request.getPhoto());
        customer.setRole(request.getRole());
        customer.setNin(request.getNin());
        return customer;
    }

    @Override
    public ApiResponse<String> updateCustomer(UUID customerId, @RequestBody CustomerRequest cst) {
//        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()){
            Customer customer = validateCustomer(customerId);
            customer.setName(cst.getName());
            customer.setEmail(cst.getEmail());
            customer.setPhone(cst.getPhone());
            customer.setAddress(cst.getAddress());
            customer.setNin(cst.getNin());
            customer.setPhoto(cst.getPhoto());
            customer.setUsername(cst.getUsername());

            customer = customerRepository.save(customer);
            return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Updated Successfully");
        }
//        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
//    }


    @Override
    public ApiResponse<String> deleteCustomer(UUID customerId) {
        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()){
            Customer customer = validateCustomer(customerId);
            customerRepository.delete(customer);
            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    @Override
    public ApiResponse<String> changeCustomerPassword(String email, changeCustomerPasswordRequest request) {
        Customer customer = customerRepository.findByEmail(email);
        if(customer.getPassword().equals(request.getOldPassword())){
            customer.setPassword(request.getNewPassword());
            customerRepository.save(customer);

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Password Changed successfully");
        }
        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Old Password");
    }

    @Override
    public ApiResponse<String> forgotCustomerPassword(String email) throws MessagingException {
        Customer customer = customerRepository.findByEmail(email);
        emailUtils.forgotMail(customer.getEmail(), "Credentials by Hotel Management System", customer.getPassword() );

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Check your email for credentials");    }

    @Override
    public ApiResponse<String> loginCustomer(String email, loginCustomerRequest request) {
        Customer customer = customerRepository.findByEmail(email);

        if (customer.getEmail().equals(request.getEmail())
                && customer.getPassword().equals(request.getPassword())) {

            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Customer login successfully");
        }

        return new ApiResponse(AppStatus.FAILED.label, HttpStatus.BAD_REQUEST.value(),
                "Incorrect Email or Password");
    }


}
