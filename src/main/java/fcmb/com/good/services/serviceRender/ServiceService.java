package fcmb.com.good.services.serviceRender;

import fcmb.com.good.model.dto.request.servicesRequest.ServicesRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.servicesResponse.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface ServiceService {


    ApiResponse<List<ServiceResponse>> getListOfService(int page, int size);

    ApiResponse<String> addService(ServicesRequest request);

    ApiResponse<ServiceResponse>getServiceById(UUID serviceId);

    ApiResponse<String> updateService(UUID serviceId, ServicesRequest request);

    ApiResponse<String> deleteService(UUID serviceId);




}
