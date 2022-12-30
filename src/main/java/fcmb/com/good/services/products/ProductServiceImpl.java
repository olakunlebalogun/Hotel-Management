package fcmb.com.good.services.products;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.filter.JwtFilter;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.productsRequest.ProductRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductResponse;
import fcmb.com.good.model.entity.products.ProductCategory;
import fcmb.com.good.model.entity.products.Products;
import fcmb.com.good.repo.products.ProductCategoryRepository;
import fcmb.com.good.repo.products.ProductRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final JwtFilter jwtFilter;



    @Override
    public ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size) {
//        if(jwtFilter.isAdmin() || jwtFilter.isEmployee()) {

            List<Products> productList = productRepository.findAll(PageRequest.of(page,size)).toList();
            if(productList.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertList(productList, ProductResponse.class));

//        }
//        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
    }


    @Override
    public ApiResponse<ProductResponse> getListOfProductByCategory(UUID productCategoryId) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findByUuid(productCategoryId);
        if(productCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        List<Products> products = productRepository.findProductsByCategory(productCategory);

        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(products, ProductResponse.class));
    }


    @Override
    public ApiResponse addProducts(ProductRequest request) {
//        if(jwtFilter.isAdmin()){


            Products product = new Products();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setQuantity(request.getQuantity());
            product.setPrice(request.getPrice());
            product.setCategory(request.getCategory());

            List<Products> productsList = productRepository.findAll();
            if (productsList.contains(product)){
                log.info("This product already exist, Please help us avoid duplicate");
                return new ApiResponse("This product already exist, Please help us avoid duplicate", HttpStatus.BAD_REQUEST.value());

            }



            ProductCategory productCategory = new ProductCategory();
            UUID uuid = UUID.randomUUID();
            productCategory.setUuid(uuid);
            productCategory.setName(product.getCategory()); // This could have been request.getCategory()

            Set<Products> productsSet = new HashSet<>();
            if (productsSet.contains(product)){
                log.info("This Category already has this product");
                return new ApiResponse("This Category already has this product", HttpStatus.BAD_REQUEST.value());

            }

            productsSet.add(product);
            productCategory.setProd(productsSet);
//
            product.setProductCategory(productCategory);

            productRepository.save(product);
            productCategoryRepository.save(productCategory);
            log.info("Both Product and Product Category saved successfully");
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(product, ProductResponse.class));
//    }
//        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
//                "You are not Authorized");
    }


    @Override
    public ApiResponse<ProductResponse> getProductById(@RequestParam("id") UUID productId) {
        if(jwtFilter.isAdmin()){

            Optional<Products> product = productRepository.findByUuid(productId);

            if(product.isEmpty())
                throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

            Products pr = product.get();
            return new ApiResponse<ProductResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(pr,ProductResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    private Products validateProducts(UUID uuid){
        Optional<Products> products = productRepository.findByUuid(uuid);
        if(products.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return products.get();
    }



    @Override
    public ApiResponse<ProductResponse> updateProduct(UUID productId, @RequestBody ProductRequest request) {
        if(jwtFilter.isAdmin()){

            Products products = validateProducts(productId);
            products.setName(request.getName());
            products.setDescription(request.getDescription());
            products.setQuantity(request.getQuantity());
            products.setPrice(request.getPrice());
            products.setCategory(request.getCategory());
            //products.setPosted_by(request.getPosted_by());
            products.setCode(request.getCode());
            products.setLocation(request.getLocation());

            products = productRepository.save(products);
            return new ApiResponse<ProductResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    Mapper.convertObject(products,ProductResponse.class));
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }

    @Override
    public ApiResponse<String> deleteProduct(UUID productId) {
        if(jwtFilter.isAdmin()){

            Products products = validateProducts(productId);
            productRepository.delete(products);
            return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                    "Record Deleted successfully");
        }
        return new ApiResponse(AppStatus.REJECT.label, HttpStatus.EXPECTATION_FAILED.value(),
                "You are not Authorized");
    }




}
