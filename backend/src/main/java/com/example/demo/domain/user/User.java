package com.example.demo.domain.user;

import com.example.demo.domain.embedded.Address;
import com.example.demo.domain.embedded.Company;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String username;
    private String email;

    @Embedded
    private Address address;

    private String phone;
    private String website;

    @Embedded
    private Company company;

    private LocalDate birth;

    public void patch(User updateUser) {
        if(updateUser == null) return;

        if(updateUser.getName() != null) {
            this.name = updateUser.getName();
        }
        if(updateUser.getUsername() != null) {
            this.username = updateUser.getUsername();
        }
        if(updateUser.getEmail() != null) {
            this.email = updateUser.getEmail();
        }
        if(updateUser.getPhone() != null) {
            this.phone = updateUser.getPhone();
        }
        if(updateUser.getWebsite() != null) {
            this.website = updateUser.getWebsite();
        }
        if(updateUser.getBirth() != null) {
            this.birth = updateUser.getBirth();
        }
        Address.patch(this.address, updateUser.getAddress());
        Company.patch(this.company, updateUser.getCompany());
    }
}
