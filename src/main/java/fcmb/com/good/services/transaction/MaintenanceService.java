package fcmb.com.good.services.transaction;

import fcmb.com.good.model.dto.request.transactionRequest.MaintenanceRequestRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.MaintenanceResponse;
import fcmb.com.good.model.entity.transaction.MaintenanceRequest;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {


    ApiResponse<List<MaintenanceResponse>> getListOfMaintenanceRequest(int page, int size);

    ApiResponse<MaintenanceResponse> addMaintenanceRequest(MaintenanceRequestRequest request);

    ApiResponse<MaintenanceResponse> getMaintenanceRequestById(UUID maintenanceId);

    ApiResponse<MaintenanceResponse> updateMaintenanceRequest(UUID maintenanceId, MaintenanceRequestRequest request);

    ApiResponse<String> deleteMaintenanceRequest(UUID maintenanceId);


}
