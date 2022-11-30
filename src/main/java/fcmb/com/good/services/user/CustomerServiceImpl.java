package fcmb.com.good.services.user;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.request.userRequest.CustomerRequest;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService  {
    private  final CustomerRepository customerRepository;

    @Value("${FILE_UPLOAD_LOCATION}")
    private String uploadLocation;

    @Override
    public ApiResponse<List<CustomerResponse>> getListOfCustomer(int page, int size) {
        List<Customer> customerList = customerRepository.findAll(PageRequest.of(page,size)).toList();
        if(customerList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(customerList, CustomerResponse.class));
    }

    @Override
    public ApiResponse<CustomerResponse> addCustomer(@RequestBody CustomerRequest request) throws IOException {

            Customer customer = Mapper.convertObject(request,Customer.class);
            customer=customerRepository.save(customer);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(customer,CustomerResponse.class));

    }



    @Override
    public ApiResponse<CustomerResponse> getCustomerById(UUID customerId) {
        Optional<Customer> customer = customerRepository.findByUuid(customerId);
        if(customer.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Customer cm = customer.get();
      return new ApiResponse<CustomerResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
              Mapper.convertObject(cm,CustomerResponse.class));

    }

//
//    private Customer validateCustomerEmail(String email){
//        List<Customer> customer = customerRepository.findByEmail(email);
//        if(customer.isEmpty())
//            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
//       return customer.get(Integer.parseInt(email.toString()));
//
//    }

    private Customer validateCustomer(UUID uuid){
    Optional<Customer> customer = customerRepository.findByUuid(uuid);
    if(customer.isEmpty())
        throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return customer.get();
    }

    @Override
    public ApiResponse<CustomerResponse> updateCustomer(UUID customerId, @RequestBody CustomerRequest cst) {
        Customer customer = validateCustomer(customerId);
        customer.setName(cst.getName());
        customer.setEmail(cst.getEmail());
        customer.setPhone(cst.getPhone());
        customer.setAddress(cst.getAddress());
        customer.setNin(cst.getNin());
        customer.setPhoto(cst.getPhoto());
        customer.setUsername(cst.getUsername());

        customer = customerRepository.save(customer);
            return new ApiResponse<CustomerResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(customer,CustomerResponse.class));
    }

    @Override
    public ApiResponse<String> deleteCustomer(UUID customerId) {
       Customer customer = validateCustomer(customerId);
        customerRepository.delete(customer);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }



}
