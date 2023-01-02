package fcmb.com.good.services.products;

import fcmb.com.good.model.dto.request.productsRequest.ProductRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size);

    ApiResponse<List<ProductResponse>> getListOfProductByName(int page, int size, String name);

    ApiResponse<ProductResponse> getListOfProductByCategory(UUID productCategoryId);

    ApiResponse<ProductResponse> addProducts(ProductRequest request);

    ApiResponse<ProductResponse> getProductById(UUID productId);

    ApiResponse<ProductResponse> updateProduct( UUID productId, ProductRequest request);

    ApiResponse<String> deleteProduct(UUID productId);




}
