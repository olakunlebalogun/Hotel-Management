package fcmb.com.good.controller.othersControllers;

import fcmb.com.good.services.others.UploadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class UploadController {

	private final UploadService uploadService;

	@PostMapping("/upload/data")
	@ApiOperation(value = "Upload profile picture of the dealer", response = String.class,
			produces = "application/json", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity uploadFile(
			@RequestPart(value = "file", required = true) MultipartFile file) throws IOException {
		return uploadService.uploadFile(file);
	}








}