package fcmb.com.good.services.transaction;

import fcmb.com.good.model.dto.request.transactionRequest.BookingReminderRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.BookingReminderResponse;
import fcmb.com.good.model.entity.transaction.BookingReminder;

import java.util.List;
import java.util.UUID;

public interface BookingReminderService {


    ApiResponse<List<BookingReminderResponse>> getListOfBookingReminder(int page, int size);

    ApiResponse<BookingReminderResponse> addBookingReminder(BookingReminderRequest request);

    ApiResponse<BookingReminderResponse> getBookingReminderById(UUID bookingReminderId);

    ApiResponse<BookingReminderResponse> updateBookingReminder( UUID bookingReminderId, BookingReminderRequest request);

    ApiResponse<String> deleteBookingReminder(UUID bookingReminderId);




}
