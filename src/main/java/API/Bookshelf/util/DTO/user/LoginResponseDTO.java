package API.Bookshelf.util.DTO.user;

import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String token;
}
