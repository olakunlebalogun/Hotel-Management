package fcmb.com.good.services.transaction;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.transactionRequest.AccountCategoryRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.AccountCategoryResponse;
import fcmb.com.good.model.entity.transaction.AccountCategory;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.transaction.AccountCategoryRepository;
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
public class AccountCategoryServiceImpl implements AccountCategoryService {
    private  final AccountCategoryRepository accountCategoryRepository;

    @Override
    public ApiResponse<List<AccountCategoryResponse>> getListOfAccountCategory(int page, int size) {
        List<AccountCategory> accountCategoryList = accountCategoryRepository.findAll(PageRequest.of(page,size)).toList();
        if(accountCategoryList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(accountCategoryList, AccountCategoryResponse.class));
    }

    @Override
    public ApiResponse<AccountCategoryResponse> addAccountCategory(@RequestBody AccountCategoryRequest request) {
        AccountCategory accountCategory = Mapper.convertObject(request,AccountCategory.class);
        accountCategory=accountCategoryRepository.save(accountCategory);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(accountCategory,AccountCategoryResponse.class));
    }

    @Override
    public ApiResponse<AccountCategoryResponse> getAccountCategoryById(@RequestParam("id") UUID accountCategoryId) {
        Optional<AccountCategory> accountCategory = accountCategoryRepository.findByUuid(accountCategoryId);

        if(accountCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        AccountCategory ac = accountCategory.get();
        return new ApiResponse<AccountCategoryResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(ac,AccountCategoryResponse.class));

    }

    private AccountCategory validateAccountCategory(UUID uuid){
        Optional<AccountCategory> accountCategory = accountCategoryRepository.findByUuid(uuid);
        if(accountCategory.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return accountCategory.get();
    }

    @Override
    public ApiResponse<AccountCategoryResponse> updateAccountCategory(@RequestParam UUID accountCategoryId,
                                                                      @RequestBody AccountCategoryRequest request) {
        AccountCategory accountCategory = validateAccountCategory(accountCategoryId);
        accountCategory.setCode(request.getCode());
        accountCategory.setCurrency(request.getCurrency());

        accountCategory = accountCategoryRepository.save(accountCategory);
        return new ApiResponse<AccountCategoryResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertObject(accountCategory,AccountCategoryResponse.class));
    }

    @Override
    public ApiResponse<String> deleteAccountCategory(UUID accountCategoryId) {
        AccountCategory accountCategory = validateAccountCategory(accountCategoryId);
        accountCategoryRepository.delete(accountCategory);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
