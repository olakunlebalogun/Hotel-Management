package fcmb.com.good.services.assets;

import fcmb.com.good.model.dto.request.assetsRequest.DamagedAssetsRequest;
import fcmb.com.good.model.dto.response.assetsResponse.DamagedAssetsResponse;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface DamagedAssetsService {

    ApiResponse<List<DamagedAssetsResponse>> getListOfDamageAssets(int page, int size);

    ApiResponse<DamagedAssetsResponse> addDamageAssets(DamagedAssetsRequest request);

    ApiResponse<DamagedAssetsResponse> getDamageAssetsById(UUID damagedAssetsId);

    ApiResponse<DamagedAssetsResponse> updateDamageAssets(UUID damagedAssetsId, DamagedAssetsRequest request);

    ApiResponse<String> deleteDamageAssets(UUID damagedAssetsId);



}
