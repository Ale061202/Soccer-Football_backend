package com.trianasalesianos.dam.Soccer.Football.user.controller;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.exception.NotPermission;
import com.trianasalesianos.dam.Soccer.Football.security.jwt.access.JwtProvider;
import com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh.RefreshToken;
import com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh.RefreshTokenException;
import com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh.RefreshTokenRequest;
import com.trianasalesianos.dam.Soccer.Football.security.jwt.refresh.RefreshTokenService;
import com.trianasalesianos.dam.Soccer.Football.user.dto.*;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
import com.trianasalesianos.dam.Soccer.Football.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;



    @Operation(summary = "Register a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Register a User",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                         "id": "c0a8015e-8698-1f14-8186-98b0a0d80001",
                                                         "username": "ale2",
                                                         "avatar": "https://robohash.org/ale",
                                                         "first_name": "Alejandro",
                                                         "last_name": "Fernandez",
                                                         "phone": "618123995",
                                                         "email": "fernandezgoale21@gmail.com",
                                                         "token": "",
                                                         "roles": [
                                                             "USER"
                                                         ]
                                                   }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Registration from a User",
                    content = @Content),
    })
    @PostMapping("/auth/register/user")
    public ResponseEntity<UserResponse> createUserWithUserRole(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUserWithUserRole(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromUser(user));
    }

    @Operation(summary = "Register of an Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Register an Admin",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                         "id": "c0a8015e-8698-11c9-8186-98b32ad20002",
                                                         "username": "admin1234",
                                                         "avatar": "https://robohash.org/admin",
                                                         "first_name": "Admin",
                                                         "last_name": "admin",
                                                         "phone": null,
                                                         "email": null,
                                                         "birthday": null,
                                                         "token": "",
                                                         "roles": [
                                                             "ADMIN"
                                                         ]
                                                  }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Registration from an Admin",
                    content = @Content),
    })
    @PostMapping("/auth/register/admin")
    public ResponseEntity<UserResponse> createUserWithAdminRole(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUserWithAdminRole(createUserRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromUser(user));
    }

    @Operation(summary = "Auth of an Admin/User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Auth of an Admin/User",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                  {
                                                        "id": "ac1b0414-867d-19db-8186-7d69f7300000",
                                                        "username": "ale",
                                                        "avatar": "https://robohash.org/ale",
                                                        "first_name": "Alejandro",
                                                        "last_name": "Fernandez",
                                                        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYzFiMDQxNC04NjdkLTE5ZGItODE4Ni03ZDY5ZjczMDAwMDAiLCJpYXQiOjE2NzcxNDMyNzIsImV4cCI6MTY3NzIxNTI3Mn0.rFNREpWGxidAGV-DNMQGhYwSV2jAMtJ2MDuNUTW-28jJWq3ticlVOiSSLMn_ymxUf5Ora19Ga_v_Wpy6odbcLw",
                                                        "refreshToken": "31bcb202-db1e-4ed3-8aed-c0f84531113d"
                                                    }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No Auth from an Admin/User",
                    content = @Content),
    })
    @Transactional
    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@RequestBody LoginRequest loginRequest) {


        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        User user = (User) authentication.getPrincipal();

        refreshTokenService.deleteByUser(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.of(user, token, refreshToken.getToken()));


    }


    @Operation(summary = "Changing password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Changing old password to a new password",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            [
                                                 {
                                                     "id": "c0a8015e-8698-11c9-8186-98b32ad20002",
                                                     "username": "admin1234",
                                                     "avatar": "https://robohash.org/admin",
                                                     "first_name": "Admin",
                                                     "last_name": "admin",
                                                     "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjMGE4MDE1ZS04Njk4LTExYzktODE4Ni05OGIzMmFkMjAwMDIiLCJpYXQiOjE2Nzc1OTkxNTYsImV4cCI6MTY3NzY3MTE1Nn0.AuN3CGm2Eejr9YlksZXyg_U5yVKt0opDxJoQw51FuetxEE66jMCDTXSyWeIuWdCiI_eV8mAz2c7rOAaspc25iA",
                                                     "refreshToken": "f2619584-202a-42ca-a477-e907205c3de7"
                                                 }
                                             ]                                         
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "No password matches",
                    content = @Content),
    })
    @PutMapping("/user/changePassword")
    public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest,
                                                       @AuthenticationPrincipal User loggedUser) {


        try {
            if (userService.passwordMatch(loggedUser, changePasswordRequest.getOldPassword())) {
                Optional<User> modified = userService.editPassword(loggedUser.getId(), changePasswordRequest.getNewPassword());
                if (modified.isPresent())
                    return ResponseEntity.ok(UserResponse.fromUser(modified.get()));
            } else {

                throw new RuntimeException();
            }
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Data Error");
        }

        return null;
    }

    @DeleteMapping("/user/{uId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID uId, @AuthenticationPrincipal User user) throws NotPermission {
        return userService.deleteUser(uId, user);
    }
}
