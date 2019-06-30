package ua.testing.demo_jpa.dto;

import lombok.*;
import ua.testing.demo_jpa.entity.RoleType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private RoleType role;
}
