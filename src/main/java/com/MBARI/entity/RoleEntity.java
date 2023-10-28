package com.MBARI.entity;










import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


/**
 * MBARI_EMPLOYEE("MBARI Employee"),
 * REGISTERED_USER("Registered User"),
 * LOGISTICS_COORDINATOR("Logistics Coordinator");
 */
@Getter
@Setter
@Entity
@Table(name = "Roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;
    @Column(name = "roleName")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> users;
}

