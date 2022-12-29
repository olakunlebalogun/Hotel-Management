package fcmb.com.good.services.products;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.filter.JwtFilter;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.productsRequest.ProductCategoryRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductCategoryResponse;
import fcmb.com.good.model.entity.products.ProductCategory;
import fcmb.com.good.repo.products.ProductCategoryRepository;
import fcmb.com.good.utills.EmailUtils;
import fcmb.com.good.utills.JwtUtil;
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
public class ProductCategoryServiceImpl implements ProductCategoryService{

    private  final ProductCategoryRepository productCategoryRepository;
    private final JwtUtil jwtUtil;
    private final JwtFilter jwtFilter;
    private final EmailUtils emailUtils;

    @Override
    public ApiResponse<List<ProductCategoryResponse>> getListOfProductCategory(int page, int size) {
        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()) {

            List<ProductCategory> productCategoryList = productCategoryRepository.findAll(PageRequest.of(page,size)).toList();
            if(productCategoryList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertList(productCategoryList, ProductCategoryResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }



    @Override
    public ApiResponse<ProductCategoryResponse> addProductCategory(@RequestBody ProductCategoryRequest request) {
        if(jwtFilter.isAdmin()){

            ProductCategory productCategory = Mapper.convertObject(request,ProductCategory.class);
            productCategory=productCategoryRepository.save(productCategory);
            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(productCategory,ProductCategoryResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    @Override
    public ApiResponse<ProductCategoryResponse> getProductCategoryById(@RequestParam("id") UUID productCategoryId) {
        if(jwtFilter.isAdmin()){

            Optional<ProductCategory> productCategory = productCategoryRepository.findByUuid(productCategoryId);
            if(productCategory.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
            ProductCategory prCategory = productCategory.get();
            return new ApiResponse<ProductCategoryResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(prCategory,ProductCategoryResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    private ProductCategory validateProductCategory(UUID uuid){
        Optional<ProductCategory> productCategory = productCategoryRepository.findByUuid(uuid);
        if(productCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return productCategory.get();
    }

    @Override
    public ApiResponse<ProductCategoryResponse> updateProductCategory(UUID productCategoryId, @RequestBody ProductCategoryRequest request) {
        if(jwtFilter.isAdmin()){

            ProductCategory productCategory = validateProductCategory(productCategoryId);
            productCategory.setName(request.getName());

            productCategory = productCategoryRepository.save(productCategory);
            return new ApiResponse<ProductCategoryResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(productCategory,ProductCategoryResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    @Override
    public ApiResponse<String> deleteProductCategory(UUID productCategoryId) {
        if(jwtFilter.isAdmin()){

            ProductCategory productCategory = validateProductCategory(productCategoryId);
            productCategoryRepository.delete(productCategory);
            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }


}
