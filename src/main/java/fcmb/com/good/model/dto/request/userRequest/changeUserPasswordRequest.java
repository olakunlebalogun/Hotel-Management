package fcmb.com.good.model.dto.request.userRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static fcmb.com.good.utills.MessageUtil.INVALID_PASSWORD;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class changePasswordRequest {

    @NotNull(message = INVALID_PASSWORD)
    @NotEmpty(message = INVALID_PASSWORD)
    String oldPassword;

    @NotNull(message = INVALID_PASSWORD)
    @NotEmpty(message = INVALID_PASSWORD)
    String newPassword;

}
