package API.Bookshelf.util.DTO.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDTO {
    @NotBlank(message = "Email can't be blank")
    private String email;
    @NotBlank(message = "Password can't be blank")
    private String password;
}
