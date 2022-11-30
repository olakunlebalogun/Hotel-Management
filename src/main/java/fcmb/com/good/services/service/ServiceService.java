package fcmb.com.good.services.service;

import fcmb.com.good.model.dto.request.servicesRequest.ServicesRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.servicesResponse.ServiceResponse;
import fcmb.com.good.model.entity.services.ServiceRequest;

import java.util.List;
import java.util.UUID;

public interface ServiceService {


    ApiResponse<List<ServiceResponse>> getListOfService(int page, int size);

    ApiResponse<ServiceResponse> addService(ServicesRequest request);

    ApiResponse<ServiceResponse>getServiceById(UUID serviceId);

    ApiResponse<ServiceResponse> updateService(UUID serviceId, ServicesRequest request);

    ApiResponse<String> deleteService(UUID serviceId);




}
