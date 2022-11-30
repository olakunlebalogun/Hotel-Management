package fcmb.com.good.controller.servicesControllers;


import fcmb.com.good.model.dto.request.servicesRequest.ServiceRequestRequest;
import fcmb.com.good.model.dto.request.servicesRequest.ServicesRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.servicesResponse.ServiceRequestResponse;
import fcmb.com.good.model.dto.response.servicesResponse.ServiceResponse;
import fcmb.com.good.model.entity.services.ServiceRequest;
import fcmb.com.good.model.entity.services.Services;
import fcmb.com.good.services.service.ServiceRequestService;
import fcmb.com.good.services.service.ServiceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static fcmb.com.good.utills.EndPoints.ServiceEndPoints.*;
import static fcmb.com.good.utills.EndpointParam.*;
import static fcmb.com.good.utills.EndpointParam.SIZE_DEFAULT;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceRequestService serviceRequestService;
    private final ServiceService serviceService;


                                    //FIND_LISTS_OF_SERVICES
    @GetMapping(FIND_SERVICE_REQUEST)
    @ApiOperation(value = "Endpoint for retrieving list of serviceRequest", response = ServiceRequestResponse.class, responseContainer = "List")
    public ApiResponse<List<ServiceRequestResponse>> getListOfServiceRequest(@RequestParam(value=PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                             @RequestParam(value=SIZE,defaultValue=SIZE_DEFAULT) int size) {
        return serviceRequestService.getListOfServiceRequest(page,size);
    }

    @GetMapping(FIND_SERVICE)
    @ApiOperation(value = "Endpoint for retrieving lists of service", response = ServiceResponse.class, responseContainer = "List")
    public ApiResponse<List<ServiceResponse>> getListOfService(@RequestParam(value=PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                               @RequestParam(value=SIZE,defaultValue=SIZE_DEFAULT) int size) {
        return serviceService.getListOfService(page,size);
    }


                                    //ADD_SERVICES
    @PostMapping(ADD_SERVICE_REQUEST)
    @ApiOperation(value = "Endpoint for adding new serviceRequest to database", response = String.class)
    public ApiResponse<ServiceRequestResponse> addServiceRequest(@Valid @RequestBody ServiceRequestRequest request) {
        return serviceRequestService.addServiceRequest(request);
    }

    @PostMapping(ADD_SERVICE)
    @ApiOperation(value = "Endpoint for adding new service to database", response = String.class)
    public ApiResponse<ServiceResponse> addService(@RequestBody ServicesRequest request) {
        return serviceService.addService(request);
    }


                                                //FIND_SERVICES_ID
    @GetMapping(FIND_SERVICE_REQUEST_BY_ID)
    @ApiOperation(value = "Endpoint for fetching serviceRequest by id from database", response = ServiceRequestResponse.class)
    public ApiResponse<ServiceRequestResponse> getServiceRequestById(@PathVariable(value = "id") UUID serviceRequest_id) {
        return serviceRequestService.getServiceRequestById(serviceRequest_id);
    }

    @GetMapping(FIND_SERVICE_BY_ID)
    @ApiOperation(value = "Endpoint for fetching service by id from database", response = ServiceResponse.class)
    public ApiResponse<ServiceResponse> getServiceById(@PathVariable(value = "id") UUID service_id) {
        return serviceService.getServiceById(service_id);
    }


                                                //UPDATE_SERVICE
    @PutMapping(UPDATE_SERVICE_REQUEST)
    @ApiOperation(value = "Endpoint for updating serviceRequest by id from database", response = String.class)
    public ApiResponse<ServiceRequestResponse> updateServiceRequest(@PathVariable(value = "id") UUID serviceRequest_id,
                                                                   @RequestBody ServiceRequestRequest request) {
        return serviceRequestService.updateServiceRequest(serviceRequest_id, request);
    }

    @PutMapping(UPDATE_SERVICE)
    @ApiOperation(value = "Endpoint for updating service by id from database", response = String.class)
    public ApiResponse<ServiceResponse> updateService(@PathVariable(value = "id") UUID service_id,
                                                      @RequestBody ServicesRequest request) {
        return serviceService.updateService(service_id, request);
    }


                                            //DELETE SERVICES
    @DeleteMapping(DELETE_SERVICE_REQUEST)
    @ApiOperation(value = "Endpoint for deleting serviceRequest by id from database", response = String.class)
    public ApiResponse<String> deleteServiceRequest(@PathVariable(value = "id") UUID serviceRequest_id) {
        return serviceRequestService.deleteServiceRequest(serviceRequest_id);
    }

    @DeleteMapping(DELETE_SERVICE)
    @ApiOperation(value = "Endpoint for deleting service by id from database", response = String.class)
    public ApiResponse<String> deleteService(@PathVariable(value = "id") UUID service_id) {
        return serviceService.deleteService(service_id);
    }




}
