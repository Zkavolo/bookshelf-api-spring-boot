package API.Bookshelf.util.DTO.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Email can't be blank")
    private String email;
    @NotBlank(message = "Password can't be blank")
    private String password;
}
