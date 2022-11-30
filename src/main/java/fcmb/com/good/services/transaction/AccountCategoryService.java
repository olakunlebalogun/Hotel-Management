package fcmb.com.good.services.transaction;

import fcmb.com.good.model.dto.request.transactionRequest.AccountCategoryRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.AccountCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface AccountCategoryService {


   ApiResponse<List<AccountCategoryResponse>> getListOfAccountCategory(int page, int size);

    ApiResponse<AccountCategoryResponse>addAccountCategory(AccountCategoryRequest request);

    ApiResponse<AccountCategoryResponse> getAccountCategoryById(UUID accountCategoryId);

    ApiResponse<AccountCategoryResponse> updateAccountCategory( UUID accountCategoryId, AccountCategoryRequest request);

    ApiResponse<String> deleteAccountCategory(UUID accountCategoryId);




}
