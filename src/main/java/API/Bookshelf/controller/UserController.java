package API.Bookshelf.controller;

import API.Bookshelf.service.UserService;
import API.Bookshelf.util.DTO.user.LoginRequestDTO;
import API.Bookshelf.util.DTO.user.RegisterRequestDTO;
import API.Bookshelf.util.error.ErrorMapper;
import API.Bookshelf.util.error.ErrorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Validated
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequest, Errors errors){
        if(errors.hasErrors()){
            ErrorResponse<?> errorResponseData = ErrorMapper.renderErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseData);
        }
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequestDTO authrequest, Errors errors){
        if(errors.hasErrors()){
            ErrorResponse<?> errorResponseData = ErrorMapper.renderErrors(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseData);
        }
        return ResponseEntity.ok(userService.login(authrequest));
    }
}
