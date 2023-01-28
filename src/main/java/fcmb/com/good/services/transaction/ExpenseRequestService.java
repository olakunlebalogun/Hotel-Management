package fcmb.com.good.services.transaction;


import fcmb.com.good.model.dto.request.transactionRequest.ExpenseRequestRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.ExpenseRequestResponse;
import fcmb.com.good.model.entity.transaction.ExpenseRequest;

import java.util.List;
import java.util.UUID;

public interface ExpenseRequestService {

    ApiResponse<List<ExpenseRequestResponse>> getListOfExpenseRequest(int page, int size);

    ApiResponse<String> addExpenseRequest(ExpenseRequestRequest request);

    ApiResponse<ExpenseRequestResponse> getExpenseRequestById(UUID expenseRequestId);

    ApiResponse<String> updateExpenseRequest( UUID expenseRequestId, ExpenseRequestRequest request);

    ApiResponse<String> deleteExpenseRequest(UUID expenseRequestId);


}
