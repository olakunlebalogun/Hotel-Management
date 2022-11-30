package fcmb.com.good.services.transaction;

import fcmb.com.good.model.dto.request.transactionRequest.BookingRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.BookingResponse;
import fcmb.com.good.model.entity.transaction.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {


    ApiResponse<List<BookingResponse>> getListOfBooking(int page, int size);

    ApiResponse<BookingResponse> addBooking(BookingRequest request);

    ApiResponse<BookingResponse> getBookingById(UUID bookingId);

    ApiResponse<BookingResponse> updateBooking( UUID bookingId, BookingRequest request);

    ApiResponse<String> deleteBooking(UUID bookingId);




}
