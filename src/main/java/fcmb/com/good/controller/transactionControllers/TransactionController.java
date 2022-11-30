package fcmb.com.good.controller.transactionControllers;


import fcmb.com.good.model.dto.request.transactionRequest.*;
import fcmb.com.good.model.dto.request.transactionRequest.ExpenseRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.*;
import fcmb.com.good.model.entity.transaction.*;
import fcmb.com.good.services.transaction.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.UUID;

import static fcmb.com.good.utills.EndPoints.TransactionEndPoints.*;
import static fcmb.com.good.utills.EndPoints.TransactionEndPoints.USERS;
import static fcmb.com.good.utills.EndpointParam.*;
import static fcmb.com.good.utills.EndpointParam.SIZE_DEFAULT;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class TransactionController {

    private final AccountCategoryService accountCategoryService;
    private final AccountChartService accountChartService;
    private final BookingReminderService bookingReminderService;
    private final BookingService bookingService;
    private final ExpenseRequestService expenseRequestService;
    private final ExpenseService expenseService;
    private final MaintenanceService maintenanceService;
    private final PaymentService paymentService;


                                            //FIND_LISTS_OF_TRANSACTIONS
    @GetMapping(FIND_ACCOUNT_CATEGORY)
    @ApiOperation(value = "Endpoint for retrieving lists of accountCategory", response = AccountCategoryResponse.class, responseContainer = "List")
    public ApiResponse<List<AccountCategoryResponse>> getListOfAccountCategory(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                               @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return accountCategoryService.getListOfAccountCategory(page, size);
    }

    @GetMapping(FIND_ACCOUNT_CHART)
    @ApiOperation(value = "Endpoint for retrieving lists of accountChart", response = AccountChartResponse.class, responseContainer = "List")
    public ApiResponse<List<AccountChartResponse>> getListOfAccountChart(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                         @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return accountChartService.getListOfAccountChart(page, size);
    }

    @GetMapping(FIND_BOOKING_REMINDER)
    @ApiOperation(value = "Endpoint for retrieving lists of bookingReminder", response = BookingReminderResponse.class, responseContainer = "List")
    public ApiResponse<List<BookingReminderResponse>> getListOfBookingReminder(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                               @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return bookingReminderService.getListOfBookingReminder(page, size);
    }

    @GetMapping(FIND_BOOKING)
    @ApiOperation(value = "Endpoint for retrieving lists of bookings", response = BookingResponse.class, responseContainer = "List")
    public ApiResponse<List<BookingResponse>> getListOfBooking(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                               @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return bookingService.getListOfBooking(page, size);
    }

    @GetMapping(FIND_EXPENSE_REQUEST)
    @ApiOperation(value = "Endpoint for retrieving lists of expenseRequest", response = ExpenseRequestResponse.class, responseContainer = "List")
    public ApiResponse<List<ExpenseRequestResponse>> getListOfExpenseRequest(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                             @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return expenseRequestService.getListOfExpenseRequest(page, size);
    }

    @GetMapping(FIND_EXPENSES)
    @ApiOperation(value = "Endpoint for retrieving lists of expenses", response = ExpenseResponse.class, responseContainer = "List")
    public ApiResponse<List<ExpenseResponse>> getListOfExpense(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                               @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return expenseService.getListOfExpense(page, size);
    }

    @GetMapping(FIND_MAINTENANCE_REQUEST)
    @ApiOperation(value = "Endpoint for retrieving lists of maintenanceRequest", response = MaintenanceResponse.class, responseContainer = "List")
    public ApiResponse<List<MaintenanceResponse>> getListOfMaintenanceRequest(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                                              @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return maintenanceService.getListOfMaintenanceRequest(page, size);
    }

    @GetMapping(FIND_PAYMENT)
    @ApiOperation(value = "Endpoint for retrieving lists of payment", response = PaymentResponse.class, responseContainer = "List")
    public ApiResponse<List<PaymentResponse>> getListOfPayment(@RequestParam(value = PAGE, defaultValue = PAGE_DEFAULT) int page,
                                                               @RequestParam(value = SIZE, defaultValue = SIZE_DEFAULT) int size) {
        return paymentService.getListOfPayment(page, size);
    }


                                             //ADD_TRANSACTIONS
    @PostMapping(ADD_ACCOUNT_CATEGORY)
    @ApiOperation(value = "Endpoint for adding new accountCategory to database", response = String.class)
    public ApiResponse<AccountCategoryResponse> addAccountCategory(@Valid @RequestBody AccountCategoryRequest request) {
        return accountCategoryService.addAccountCategory(request);
    }

    @PostMapping(ADD_ACCOUNT_CHART)
    @ApiOperation(value = "Endpoint for adding new accountChart to database", response = String.class)
    public ApiResponse<AccountChartResponse> addAccountChart(@Valid @RequestBody AccountChartRequest request) {
        return accountChartService.addAccountChart(request);
    }

    @PostMapping(ADD_BOOKING_REMINDER)
    @ApiOperation(value = "Endpoint for adding new bookingReminder to database", response = String.class)
    public ApiResponse<BookingReminderResponse> addBookingReminder(@Valid @RequestBody BookingReminderRequest request) {
        return bookingReminderService.addBookingReminder(request);
    }

    @PostMapping(ADD_BOOKING)
    @ApiOperation(value = "Endpoint for adding new booking to database", response = String.class)
    public ApiResponse<BookingResponse> addBooking(@Valid @RequestBody BookingRequest request) {
        return bookingService.addBooking(request);
    }

    @PostMapping(ADD_EXPENSE_REQUEST)
    @ApiOperation(value = "Endpoint for adding new expenseRequest to database", response = String.class)
    public ApiResponse<ExpenseRequestResponse> addExpenseRequest(@Valid @RequestBody ExpenseRequestRequest request) {
        return expenseRequestService.addExpenseRequest(request);
    }

    @PostMapping(ADD_EXPENSES)
    @ApiOperation(value = "Endpoint for adding new expenses to database", response = String.class)
    public ApiResponse<ExpenseResponse> addExpense(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.addExpense(request);
    }

    @PostMapping(ADD_MAINTENANCE_REQUEST)
    @ApiOperation(value = "Endpoint for adding new maintenanceRequest to database", response = String.class)
    public ApiResponse<MaintenanceResponse> addMaintenanceRequest(@Valid @RequestBody MaintenanceRequestRequest request) {
        return maintenanceService.addMaintenanceRequest(request);
    }

    @PostMapping(ADD_PAYMENT)
    @ApiOperation(value = "Endpoint for adding new payment to database", response = String.class)
    public ApiResponse<PaymentResponse> addPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.addPayment(request);
    }



                                            //FIND_TRANSACTIONS_BY_ID
    @GetMapping(FIND_ACCOUNT_CATEGORY_BY_ID)
    @ApiOperation(value = "Endpoint for fetching accountCategory by id from database", response = AccountCategoryResponse.class)
    public ApiResponse<AccountCategoryResponse> getAccountCategoryById(@PathVariable(value = "id") UUID accountCategory_id) {
        return accountCategoryService.getAccountCategoryById(accountCategory_id);
    }

    @GetMapping(FIND_ACCOUNT_CHART_BY_ID)
    @ApiOperation(value = "Endpoint for fetching accountChart by id from database", response = AccountChartResponse.class)
    public ApiResponse<AccountChartResponse> getAccountChartById(@PathVariable(value = "id") UUID accountChart_id) {
        return accountChartService.getAccountChartById(accountChart_id);
    }

    @GetMapping(FIND_BOOKING_REMINDER_BY_ID)
    @ApiOperation(value = "Endpoint for fetching bookingReminder by id from database", response = BookingReminderResponse.class)
    public ApiResponse<BookingReminderResponse> getBookingReminderById(@PathVariable(value = "id") UUID bookingReminder_id) {
        return bookingReminderService.getBookingReminderById(bookingReminder_id);
    }

    @GetMapping(FIND_BOOKING_BY_ID)
    @ApiOperation(value = "Endpoint for fetching booking by id from database", response = BookingResponse.class)
    public ApiResponse<BookingResponse> getBookingById(@PathVariable(value = "id") UUID booking_id) {
        return bookingService.getBookingById(booking_id);
    }

    @GetMapping(FIND_EXPENSE_REQUEST_BY_ID)
    @ApiOperation(value = "Endpoint for fetching expenseRequest by id from database", response = ExpenseRequestResponse.class)
    public ApiResponse<ExpenseRequestResponse> getExpenseRequestById(@PathVariable(value = "id") UUID expenseRequest_id) {
        return expenseRequestService.getExpenseRequestById(expenseRequest_id);
    }

    @GetMapping(FIND_EXPENSES_BY_ID)
    @ApiOperation(value = "Endpoint for fetching expenses by id from database", response = ExpenseResponse.class)
    public ApiResponse<ExpenseResponse> getExpenseById(@PathVariable(value = "id") UUID expense_id) {
        return expenseService.getExpenseById(expense_id);
    }

    @GetMapping(FIND_MAINTENANCE_REQUEST_BY_ID)
    @ApiOperation(value = "Endpoint for fetching maintenanceRequest by id from database", response = MaintenanceResponse.class)
    public ApiResponse<MaintenanceResponse> getMaintenanceRequestById(@PathVariable(value = "id") UUID maintenance_id) {
        return maintenanceService.getMaintenanceRequestById(maintenance_id);
    }

    @GetMapping(FIND_PAYMENT_BY_ID)
    @ApiOperation(value = "Endpoint for fetching payment by id from database", response = PaymentResponse.class)
    public ApiResponse<PaymentResponse> getPaymentById(@PathVariable(value = "id") UUID payment_id) {
        return paymentService.getPaymentById(payment_id);
    }



                                            //UPDATE_USERS
    @PutMapping(UPDATE_ACCOUNT_CATEGORY)
    @ApiOperation(value = "Endpoint for updating accountCategory by id from database", response = String.class)
    public ApiResponse<AccountCategoryResponse> updateAccountCategory(@PathVariable(value = "id") UUID accountCategory_id,
                                                                     @RequestBody AccountCategoryRequest request) {
        return accountCategoryService.updateAccountCategory(accountCategory_id, request);
    }

    @PutMapping(UPDATE_ACCOUNT_CHART)
    @ApiOperation(value = "Endpoint for updating accountChart by id from database", response = String.class)
    public ApiResponse<AccountChartResponse> updateAccountChart(@PathVariable(value = "id") UUID accountChart_id,
                                                               @RequestBody AccountChartRequest request) {
        return accountChartService.updateAccountChart(accountChart_id, request);
    }

    @PutMapping(UPDATE_BOOKING_REMINDER)
    @ApiOperation(value = "Endpoint for updating bookingReminder by id from database", response = String.class)
    public ApiResponse<BookingReminderResponse> updateBookingReminder(@PathVariable(value = "id") UUID bookingReminder_id,
                                                                     @RequestBody BookingReminderRequest request) {
        return bookingReminderService.updateBookingReminder(bookingReminder_id, request);
    }

    @PutMapping(UPDATE_BOOKING)
    @ApiOperation(value = "Endpoint for updating booking by id from database", response = String.class)
    public ApiResponse<BookingResponse> updateBooking(@PathVariable(value = "id") UUID booking_id,
                                                     @RequestBody BookingRequest request) {
        return bookingService.updateBooking(booking_id, request);
    }

    @PutMapping(UPDATE_EXPENSE_REQUEST)
    @ApiOperation(value = "Endpoint for updating expenseRequest by id from database", response = String.class)
    public ApiResponse<ExpenseRequestResponse> updateExpenseRequest(@PathVariable(value = "id") UUID expenseRequest_id,
                                                                   @RequestBody ExpenseRequestRequest request) {
        return expenseRequestService.updateExpenseRequest(expenseRequest_id, request);
    }

    @PutMapping(UPDATE_EXPENSES)
    @ApiOperation(value = "Endpoint for updating expenses by id from database", response = String.class)
    public ApiResponse<ExpenseResponse> updateExpense(@PathVariable(value = "id") UUID expenses_id,
                                                     @RequestBody ExpenseRequest request) {
        return expenseService.updateExpense(expenses_id, request);
    }

    @PutMapping(UPDATE_MAINTENANCE_REQUEST)
    @ApiOperation(value = "Endpoint for updating maintenanceRequest by id from database", response = String.class)
    public ApiResponse<MaintenanceResponse> updateMaintenanceRequest(@PathVariable(value = "id") UUID maintenanceRequest_id,
                                                                    @RequestBody MaintenanceRequestRequest request) {
        return maintenanceService.updateMaintenanceRequest(maintenanceRequest_id, request);
    }

    @PutMapping(UPDATE_PAYMENT)
    @ApiOperation(value = "Endpoint for updating payment by id from database", response = String.class)
    public ApiResponse<PaymentResponse> updatePayment(@PathVariable(value = "id") UUID payment_id,
                                                     @RequestBody PaymentRequest request) {
        return paymentService.updatePayment(payment_id, request);
    }



                                            //DELETE_TRANSACTIONS
    @DeleteMapping(DELETE_ACCOUNT_CATEGORY)
    @ApiOperation(value = "Endpoint for deleting accountCategory by id from database", response = String.class)
    public ApiResponse<String> deleteAccountCategory(@PathVariable(value = "id") UUID accountCategory_id) {
        return accountCategoryService.deleteAccountCategory(accountCategory_id);
    }

    @DeleteMapping(DELETE_ACCOUNT_CHART)
    @ApiOperation(value = "Endpoint for deleting accountChart by id from database", response = String.class)
    public ApiResponse<String> deleteAccountChart(@PathVariable(value = "id") UUID accountChart_id) {
        return accountChartService.deleteAccountChart(accountChart_id);
    }

    @DeleteMapping(DELETE_BOOKING_REMINDER)
    @ApiOperation(value = "Endpoint for deleting bookingReminder by id from database", response = String.class)
    public ApiResponse<String> deleteBookingReminder(@PathVariable(value = "id") UUID bookingReminder_id) {
        return bookingReminderService.deleteBookingReminder(bookingReminder_id);
    }

    @DeleteMapping(DELETE_BOOKING)
    @ApiOperation(value = "Endpoint for deleting booking by id from database", response = String.class)
    public ApiResponse<String> deleteBooking(@PathVariable(value = "id") UUID booking_id) {
        return bookingService.deleteBooking(booking_id);
    }

    @DeleteMapping(DELETE_EXPENSE_REQUEST)
    @ApiOperation(value = "Endpoint for deleting expenseRequest by id from database", response = String.class)
    public ApiResponse<String> deleteExpenseRequest(@PathVariable(value = "id") UUID expenseRequest_id) {
        return expenseRequestService.deleteExpenseRequest(expenseRequest_id);
    }

    @DeleteMapping(DELETE_EXPENSES)
    @ApiOperation(value = "Endpoint for deleting expenses by id from database", response = String.class)
    public ApiResponse<String> deleteExpense(@PathVariable(value = "id") UUID expense_id) {
        return expenseService.deleteExpense(expense_id);
    }

    @DeleteMapping(DELETE_MAINTENANCE_REQUEST)
    @ApiOperation(value = "Endpoint for deleting maintenanceRequest by id from database", response = String.class)
    public ApiResponse<String> deleteUser(@PathVariable(value = "id") UUID maintenance_id) {
        return maintenanceService.deleteMaintenanceRequest(maintenance_id);
    }

    @DeleteMapping(DELETE_PAYMENT)
    @ApiOperation(value = "Endpoint for deleting payment by id from database", response = String.class)
    public ApiResponse<String> deletePayment(@PathVariable(value = "id") UUID payment_id) {
        return paymentService.deletePayment(payment_id);
    }



}