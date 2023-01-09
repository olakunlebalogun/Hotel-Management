package fcmb.com.good.services.assets;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.assetsRequest.AssetsCategoryRequest;
import fcmb.com.good.model.dto.response.assetsResponse.AssetsCategoryResponse;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.assets.Assets;
import fcmb.com.good.model.entity.assets.AssetsCategory;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.assets.AssetsCategoryRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetsCategoryServiceImpl implements AssetsCategoryService {
    private final AssetsCategoryRepository assetsCategoryRepository;

    @Override
    public ApiResponse<List<AssetsCategoryResponse>> getListOfAssetsCategory(int page, int size) {
        List<AssetsCategory> assetsCategoryList = assetsCategoryRepository.findAll(PageRequest.of(page,size)).toList();
        if(assetsCategoryList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(assetsCategoryList, AssetsCategoryResponse.class));
    }

    @Override
    public ApiResponse<String> addAssetsCategory(@RequestBody AssetsCategoryRequest request) {
        AssetsCategory assetsCategory = Mapper.convertObject(request,AssetsCategory.class);
        assetsCategory=assetsCategoryRepository.save(assetsCategory);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");
    }

    @Override
    public ApiResponse<AssetsCategoryResponse> getAssetsCategoryById(@PathVariable UUID assetsCategoryId) {
        Optional<AssetsCategory> assetsCategory = assetsCategoryRepository.findByUuid(assetsCategoryId);
        if(assetsCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        AssetsCategory as = assetsCategory.get();
        return new ApiResponse<AssetsCategoryResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(as, AssetsCategoryResponse.class));
    }

    private AssetsCategory validateAssetsCategory(UUID uuid){
        Optional<AssetsCategory> assetsCategory = assetsCategoryRepository.findByUuid(uuid);
        if(assetsCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return assetsCategory.get();
    }

    @Override
    public ApiResponse<String> updateAssetsCategory(UUID assetsCategoryId,
                                                                    @RequestBody AssetsCategoryRequest request) {
        AssetsCategory assetsCategory1 = validateAssetsCategory(assetsCategoryId);
        assetsCategory1.setName(request.getName());
        assetsCategory1.setType(request.getType());
        assetsCategory1.setDescription(request.getDescription());
        assetsCategory1.setAccount_no(request.getAccount_no());

        assetsCategory1 = assetsCategoryRepository.save(assetsCategory1);
        return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record updated successfully");
    }

    @Override
    public ApiResponse<String> deleteAssetsCategory(UUID assetsCategoryId) {
        AssetsCategory assetsCategory = validateAssetsCategory(assetsCategoryId);
        assetsCategoryRepository.delete(assetsCategory);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }




}

