package de.ait.timepad.dto;

import de.ait.timepad.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пользователь системы")
public class UserDto {

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema(description = "Email пользователя", example = "simple@mail.com" )
    private String email;

    @Schema(description = "Роль пользователя - ADMIN - администратор, USER - пользователь, MANAGER - менеджер", example = "ADMIN")
    private String role;

    @Schema(description = "Статус пользователя - NOT_CONFIRMED - не подтвержден, " +
            "CONFIRMED - подтвержден, BANNED - забанен, DELETED - удален", example = "CONFIRMED")
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
