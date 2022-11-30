package fcmb.com.good.services.others;

import fcmb.com.good.model.entity.user.Customer;
import fcmb.com.good.repo.user.CustomerRepository;
import fcmb.com.good.utills.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
@RequiredArgsConstructor
public class UploadServiceImpl  implements UploadService {

    @NonNull
    private CustomerRepository customerRepository;

    @Value("${FILE_UPLOAD_LOCATION}")
    private String uploadLocation;



    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file) throws IOException {
        File uploadedFile = saveFile(file);
        String result = readSave(uploadedFile);
        return ResponseEntity.ok(result);
    }


    private String readSave(File file) {
        Customer cus = new Customer();
        cus.setPhoto(cus.getPhoto());
        customerRepository.save(cus);

        return "Record successfully saved to database";
    }


    private File saveFile(MultipartFile file) {
        File testFile = null;
        try {
            String originalName = file.getOriginalFilename().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");
            String files = getStoreLocationPath() + File.separator + Utils.getNewFileName(getStoreLocationPath(), originalName);
            testFile = new File(files);
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());

            return testFile;

        } catch (IOException e) {
            e.printStackTrace();
            return testFile;

        }

    }


    private String getStoreLocationPath() {
        return Utils.baseDir(uploadLocation).getPath();
    }
}
