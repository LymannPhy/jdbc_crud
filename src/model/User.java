package model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String uuid; // Change the data type from UUID to String
    private String userName;
    private String userEmail;
    private String userPassword;
    private Boolean isDeleted;
    private Boolean isVerified;
}

