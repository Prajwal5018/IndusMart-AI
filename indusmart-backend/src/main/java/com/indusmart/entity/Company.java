package com.indusmart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(unique = true)
    private String gstNumber;

    @Column(unique = true)
    private String panNumber;

    private String email;

    private String phone;

    private String website;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    @Column(length = 1000)
    private String description;

    private String logoUrl;

    private boolean verified;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User owner;
}