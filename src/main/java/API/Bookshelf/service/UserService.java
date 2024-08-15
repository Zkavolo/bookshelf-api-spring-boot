package API.Bookshelf.service;

import API.Bookshelf.util.DTO.user.LoginRequestDTO;
import API.Bookshelf.util.DTO.user.LoginResponseDTO;
import API.Bookshelf.util.DTO.user.RegisterRequestDTO;
import API.Bookshelf.util.DTO.user.RegisterResponseDTO;

public interface UserService {
    RegisterResponseDTO register(RegisterRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);
}
