package fcmb.com.good.services.products;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.filter.JwtFilter;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.productsRequest.ProductRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductResponse;
import fcmb.com.good.model.entity.products.ProductCategory;
import fcmb.com.good.model.entity.products.Product;
import fcmb.com.good.model.entity.products.ProductPurchase;
import fcmb.com.good.model.entity.user.AppUser;
import fcmb.com.good.repo.products.ProductCategoryRepository;
import fcmb.com.good.repo.products.ProductRepository;
import fcmb.com.good.repo.user.UserRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final JwtFilter jwtFilter;
private final UserRepository userRepository;

    @Override
    public ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size) {
//        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()) {

            List<Product> productList = productRepository.findAll(PageRequest.of(page,size)).toList();
            if(productList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertList(productList, ProductResponse.class));

//        }
//        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
    }

    @Override
    public ApiResponse<List<ProductResponse>> getListOfProductByName(int page, int size, String name) {

        List<Product> productList = new ArrayList<>();//productRepository.findByName(String.valueOf(PageRequest.of(page,size)));

        log.info("List of products by name");

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(productList, ProductResponse.class));
    }

    @Override
    public ApiResponse<ProductResponse> getListOfProductByCategory(UUID productCategoryId) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findByUuid(productCategoryId);
        if(productCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        List<Product> products = productRepository.findProductsByCategory(productCategory);

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(products, ProductResponse.class));
    }

private void validateDuplicationProduct(String name){
        Optional<Product> existingProduct = productRepository.findByName(name);
        if(existingProduct.isPresent())
            throw new RecordNotFoundException("Duplicate record");
}
    @Override
    public ApiResponse<String> updateProduct(UUID productId,ProductRequest request) {
        if(jwtFilter.isAdmin()){

            Product product = validateProducts(productId);
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setQuantity(product.getQuantity()+request.getQuantity());
            product.setPrice(request.getPrice());
            //products.setCategory(request.getCategory());
            //products.setPosted_by(request.getPosted_by());
            product.setCode(request.getCode());
            product.setLocation(request.getLocation());

            product = productRepository.save(product);
            return new ApiResponse<String>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record updated successfully");
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }
    @Override
    /**
     * @Validate that no duplicate product allow*
     * @Validate that product category exists otherwise return record not found*
     * @Validate that user creating the product exists, otherwise return user not found*
     * Create the product definition and save
     * @return success message* *
     * * */
    public ApiResponse<String> addProducts(ProductRequest request) {
        validateDuplicationProduct(request.getName());
        ProductCategory existingCategory = productCategoryRepository.findByUuid(request.getCategory())
                                    .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
        AppUser existingUser  = userRepository.findByUuid(request.getCreatedBy())
                                    .orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setQuantity(request.getQuantity());
            product.setPrice(request.getPrice());
            product.setCategory(existingCategory.getUuid().toString());
            product.setCode(request.getCode());
            product.setLocation(request.getLocation());
            product.setProductCategory(existingCategory);
            product.setStatus(request.getStatus());
            product.setCreatedBy(existingUser);
            productRepository.save(product);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");
    }


    @Override
    public ApiResponse<ProductResponse> getProductById(@RequestParam("id") UUID productId) {
        if(jwtFilter.isAdmin()){

            Optional<Product> productOptional = productRepository.findByUuid(productId);

            if(productOptional.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            Product pr = productOptional.get();
            return new ApiResponse<ProductResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(pr,ProductResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    private Product validateProducts(UUID uuid){
        return productRepository.findByUuid(uuid).orElseThrow(()->new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
    }




    @Override
    public ApiResponse<String> deleteProduct(UUID productId) {
        if(jwtFilter.isAdmin()){

            Product product = validateProducts(productId);
            productRepository.delete(product);
            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }




}
