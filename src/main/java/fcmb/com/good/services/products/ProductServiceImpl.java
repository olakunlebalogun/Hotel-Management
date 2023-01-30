package fcmb.com.good.services.products;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.productsRequest.ProductRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductResponse;
import fcmb.com.good.model.entity.products.Product;
import fcmb.com.good.model.entity.products.ProductCategory;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final UserRepository userRepository;

    /**
     * Finding the list of all products*
     * Validate if the List of products is empty otherwise return record not found*
     * @return the list of products and a Success Message* *
     * * */
    @Override
    public ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size) {

        List<Product> productList = productRepository.findAll(PageRequest.of(page, size)).toList();
        if (productList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(productList, ProductResponse.class));

    }


    @Override
    /**
     * @Search the list of all products by name
     * Validate if the List of products is empty otherwise return record not found*
     * @return the list of products by name
     * * */
    public List<Product> searchProductsByName(String name) {
        List<Product> searchProductsByName = productRepository.searchProductsByName(name);

        if (searchProductsByName.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return searchProductsByName;
    }

    @Override
    /**
     * @Search the list of all products by category*
     * Validate if the List of productCategory is empty otherwise return record not found*
     * @return the list of products by categoryName* *
     *
     * */
    public List<Product> searchProductsByProductCategory(String productCategory) {
        List<Product> searchProductsByProductCategory = productRepository.searchProductsByProductCategory(productCategory);

        if (searchProductsByProductCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return searchProductsByProductCategory;

    }


    @Override
    /**
     * Finding the list of all productOptional by uuid*
     * Validate if the List of productOptional is empty otherwise return record not found
     * Create the product definition and get the product Optional by uuid
     * @return the list of products and a Success Message* *
     * * */
    public ApiResponse<ProductResponse> getProductById(@RequestParam("id") UUID productId) {
        Optional<Product> productOptional = productRepository.findByUuid(productId);
        if (productOptional.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        Product product = productOptional.get();
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(product, ProductResponse.class));
    }


    /**
     * @return product
     * *
     * validating products by uuid*
     * Validate if products is empty otherwise return record not found
     */
    private Product validateProducts(UUID uuid) {
        return productRepository.findByUuid(uuid).orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));
    }

    /**
     * Validating existingProductOptional by name*
     * Validate if the List of existingProductOptional is empty otherwise return Duplicate Record*
     * *
     */

    private void validateDuplicationProduct(String name) {
        Optional<Product> existingProduct = productRepository.findByName(name);
        if (existingProduct.isPresent())
            throw new RecordNotFoundException("Duplicate record");
    }


    /**
     * @return success message* *
     * *
     * Validate that no duplicate product allow*
     * Validate that product category exists otherwise return record not found*
     * Validate that user creating the product exists, otherwise return user not found*
     * Create the product definition and save
     */
    @Override
    public ApiResponse<String> addProducts(ProductRequest request) {
        validateDuplicationProduct(request.getName());

        ProductCategory existingProductCategory = productCategoryRepository.findByUuid(request.getCategory())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        AppUser existingUser = userRepository.findByUuid(request.getCreatedBy())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setCode(request.getCode());
        product.setLocation(request.getLocation());
        product.setProductCategory(existingProductCategory);
        product.setStatus(request.getStatus());
        product.setCreatedBy(existingUser);
        product.setPurchasedPrice(request.getPurchasedPrice());
        product.setProfit(product.getPurchasedPrice() - request.getPrice());
        product.setProductsCategory(request.getProductsCategory());

        productRepository.save(product);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record created successfully");
    }


    @Override
    /**
     * Validating the list of existingProductCategory by uuid*
     * Validate if the List of existingProductCategory is empty otherwise return record not found
     * Create the product definition and save
     * @return a Success Message* *
     * * */
    public ApiResponse<String> updateProduct(UUID productId, ProductRequest request) {

        ProductCategory existingProductCategory = productCategoryRepository.findByUuid(request.getCategory())
                .orElseThrow(() -> new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND));

        Product product = validateProducts(productId);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(product.getQuantity() + request.getQuantity());
        product.setPrice(request.getPrice());
        product.setCode(request.getCode());
        product.setLocation(request.getLocation());
        product.setPurchasedPrice(request.getPurchasedPrice());
        product.setStatus(request.getStatus());
        product.setProductCategory(existingProductCategory);
        product.setStatus(request.getStatus());
        product.setPurchasedPrice(request.getPurchasedPrice());
        product.setProductsCategory(request.getProductsCategory());

        productRepository.save(product);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record updated successfully");
    }


    @Override
    /**
     * validating products by uuid*
     * Validate if products is empty otherwise return record not found
     * @Delete product
     * @return a Success Message* *
     * * */
    public ApiResponse<String> deleteProduct(UUID productId) {
        Product product = validateProducts(productId);
        productRepository.delete(product);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }

    public void addPurchasedProductsToProduct (ProductPurchase productPurchase){
        Integer purchasedQuantity = productPurchase.getQuantity();
        Product purchasedProduct = productPurchase.getProduct();
        Integer productQuantity = purchasedProduct.getQuantity();
        productQuantity += purchasedQuantity;
        purchasedProduct.setQuantity(productQuantity);
        log.info("The quantity of product purchased: {}", purchasedQuantity);
        log.info("The quantity of updated product: {}", productQuantity);
    }


}
