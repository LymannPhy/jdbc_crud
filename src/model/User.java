package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private UUID uuid;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Boolean isDeleted;
    private Boolean is_verified;
}
