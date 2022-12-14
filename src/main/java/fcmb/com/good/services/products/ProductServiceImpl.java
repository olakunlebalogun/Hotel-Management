package fcmb.com.good.services.products;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.productsRequest.ProductRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.products.Products;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.products.ProductRepository;
import fcmb.com.good.utills.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;

    @Override
    public ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size) {
        List<Products> productList = productRepository.findAll(PageRequest.of(page,size)).toList();
        if(productList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(productList, ProductResponse.class));
    }

    @Override
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        Products products = Mapper.convertObject(request,Products.class);
        products=productRepository.save(products);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(products,ProductResponse.class));
    }

    @Override
    public ApiResponse<ProductResponse> getProductById(@RequestParam("id") UUID productId) {
        Optional<Products> product = productRepository.findByUuid(productId);

        if(product.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        Products pr = product.get();
        return new ApiResponse<ProductResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(pr,ProductResponse.class));

    }

    private Products validateProducts(UUID uuid){
        Optional<Products> products = productRepository.findByUuid(uuid);
        if(products.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return products.get();
    }

    @Override
    public ApiResponse<ProductResponse> updateProduct(UUID productId, @RequestBody ProductRequest request) {
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

    @Override
    public ApiResponse<String> deleteProduct(UUID productId) {
        Products products = validateProducts(productId);
        productRepository.delete(products);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }



}
