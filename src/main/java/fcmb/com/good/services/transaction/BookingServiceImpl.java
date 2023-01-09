package fcmb.com.good.services.transaction;

import fcmb.com.good.exception.RecordNotFoundException;
import fcmb.com.good.mapper.Mapper;
import fcmb.com.good.model.dto.enums.AppStatus;
import fcmb.com.good.model.dto.request.transactionRequest.BookingRequest;
import fcmb.com.good.model.dto.response.othersResponse.ApiResponse;
import fcmb.com.good.model.dto.response.transactionResponse.BookingResponse;
import fcmb.com.good.model.dto.response.userResponse.CustomerResponse;
import fcmb.com.good.model.entity.transaction.Booking;
import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.transaction.BookingRepository;
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
public class BookingServiceImpl implements BookingService {
    private  final BookingRepository bookingRepository;

    @Override
    public ApiResponse<List<BookingResponse>> getListOfBooking(int page, int size) {
        List<Booking> bookingList = bookingRepository.findAll(PageRequest.of(page,size)).toList();
        if(bookingList.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);

        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                Mapper.convertList(bookingList, BookingResponse.class));
    }

    @Override
    public ApiResponse<String> addBooking(@RequestBody BookingRequest request) {
        Booking booking = Mapper.convertObject(request,Booking.class);
        booking=bookingRepository.save(booking);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record added Successfully");
    }

    @Override
    public ApiResponse<BookingResponse> getBookingById(@RequestParam("id") UUID bookingId) {
        Optional<Booking> booking = bookingRepository.findByUuid(bookingId);

        if(booking.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        Booking book = booking.get();
        return new ApiResponse<BookingResponse>(AppStatus.SUCCESS.label, HttpStatus.OK.value(), Mapper.convertObject(book,BookingResponse.class));

    }

    private Booking validateBooking(UUID uuid){
        Optional<Booking> booking = bookingRepository.findByUuid(uuid);
        if(booking.isEmpty())
            throw new RecordNotFoundException(MessageUtil.RECORD_NOT_FOUND);
        return booking.get();
    }

    @Override
        public ApiResponse<String> updateBooking(UUID bookingId, @RequestBody BookingRequest request) {
        Booking booking = validateBooking(bookingId);
        booking.setCustomer_id(request.getCustomer_id());
        booking.setRoom_id(request.getRoom_id());
        booking.setPrice(request.getPrice());
        booking.setQuantity(request.getQuantity());
        booking.setCheck_in_date(request.getCheck_in_date());
        booking.setCheck_out_date(request.getCheck_out_date());
        booking.setNight(request.getNight());

        booking = bookingRepository.save(booking);
        return new ApiResponse<>(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Updated Successfully");
    }

    @Override
    public ApiResponse<String> deleteBooking(UUID bookingId) {
        Booking booking = validateBooking(bookingId);
        bookingRepository.delete(booking);
        return new ApiResponse(AppStatus.SUCCESS.label, HttpStatus.OK.value(),
                "Record Deleted successfully");
    }


}
