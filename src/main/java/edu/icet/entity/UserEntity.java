package edu.icet.entity;

import edu.icet.util.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;

    public UserEntity(String name,String email,String password,UserRole userRole) {
        this.userRole = userRole;
        this.password = password;
        this.email = email;
        this.name = name;
    }
}
