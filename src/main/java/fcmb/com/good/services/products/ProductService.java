package fcmb.com.good.services.products;

import fcmb.com.good.model.dto.request.productsRequest.ProductRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.productsResponse.ProductResponse;
import fcmb.com.good.model.entity.products.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ApiResponse<List<ProductResponse>> getListOfProduct(int page, int size);

    List<Product> searchProductsByCategory(String category);

    ApiResponse<String> addProducts(ProductRequest request);

    ApiResponse<ProductResponse> getProductById(UUID productId);

    ApiResponse<String> updateProduct(UUID productId, ProductRequest request);

    ApiResponse<String> deleteProduct(UUID productId);

    List<Product> searchProductsByName(String name);


}
