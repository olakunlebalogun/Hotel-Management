package fcmb.com.good.controller.othersControllers;

import fcmb.com.good.model.entity.others.Document;
import fcmb.com.good.services.others.UploadService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class UploadController {

	private final UploadService uploadService;

	@PostMapping("/upload/data")
	@ApiOperation(value = "Upload profile picture of the user", response = String.class,
			produces = "application/json", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public Document uploadFile(@RequestPart(value = "file", required = true) MultipartFile file) throws IOException {
		return uploadService.uploadFile(file);
	}

	@GetMapping("/download/data")
	@ApiOperation(value = "Download picture of User", response = String.class,
			produces = "application/json", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public byte[] downLoadFile(@PathVariable(value = "image", required = true) String fileName) throws IOException {
		return uploadService.downloadPhoto(fileName);
	}




}