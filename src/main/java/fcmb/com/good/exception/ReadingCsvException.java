package fcmb.com.good.exception;

import java.io.IOException;

public class ReadingCsvException extends IOException {
    private String message;
    public ReadingCsvException(String message){
        super(message);
        this.message=message;
    }
    public ReadingCsvException(){

    }

}
