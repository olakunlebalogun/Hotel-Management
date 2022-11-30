package fcmb.com.good.services.service;

import fcmb.com.good.model.dto.request.servicesRequest.ServiceRequestRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.servicesResponse.ServiceRequestResponse;
import fcmb.com.good.model.entity.services.ServiceRequest;

import java.util.List;
import java.util.UUID;

public interface ServiceRequestService {

    ApiResponse<List<ServiceRequestResponse>> getListOfServiceRequest(int page, int size);

    ApiResponse<ServiceRequestResponse> addServiceRequest(ServiceRequestRequest request);

    ApiResponse<ServiceRequestResponse> getServiceRequestById(UUID serviceRequestId);

    ApiResponse<ServiceRequestResponse> updateServiceRequest( UUID serviceRequestId, ServiceRequestRequest request);

    ApiResponse<String> deleteServiceRequest(UUID serviceRequestId);



}
