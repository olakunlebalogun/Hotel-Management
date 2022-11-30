package fcmb.com.good.services.assets;


import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.assetsRequest.AssetsRequest;
import fcmb.com.good.model.dto.response.assetsResponse.AssetsResponse;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.entity.assets.Assets;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.assets.AssetsRepository;
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
public class AssetsServiceImp implements AssetsService {
    private final AssetsRepository assetsRepository;

    @Override
    public ApiResponse<List<AssetsResponse>> getListOfAssets(int page, int size) {
        List<Assets> assetsList = assetsRepository.findAll(PageRequest.of(page,size)).toList();
        if(assetsList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(assetsList, AssetsResponse.class));
    }

    @Override
    public ApiResponse<AssetsResponse> addAssets(@RequestBody AssetsRequest request) {
        Assets assets = Mapper.convertObject(request,Assets.class);
        assets=assetsRepository.save(assets);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(assets, AssetsResponse.class));
    }

    @Override
    public ApiResponse<AssetsResponse> getAssetsById(@RequestParam UUID assets_id) {
        Optional<Assets> assets = assetsRepository.findByUuid(assets_id);
        if(assets.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Assets cm = assets.get();
        return new ApiResponse<AssetsResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(cm, AssetsResponse.class));
    }

    private Assets validateAssets(UUID uuid){
        Optional<Assets> assets = assetsRepository.findByUuid(uuid);
        if(assets.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return assets.get();
    }

    @Override
    public ApiResponse<AssetsResponse> updateAssets(UUID assets_id, @RequestBody AssetsRequest request) {
        Assets assets = validateAssets(assets_id);
        assets.setCategory_id(request.getCategory_id());
        assets.setAsset_name(request.getAsset_name());
        assets.setValue_cost(request.getValue_cost());
        assets.setDescription(request.getDescription());
        assets.setRecord_id(request.getRecord_id());
        assets.setRecord_type(request.getRecord_type());
        assets.setCode(request.getCode());
        assets.setLocation(request.getLocation());

        assets = assetsRepository.save(assets);
        return new ApiResponse<AssetsResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(assets,AssetsResponse.class));
    }

    @Override
    public ApiResponse<String> deleteAssets(UUID assets_id) {
        Assets assets = validateAssets(assets_id);
        assetsRepository.delete(assets);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
