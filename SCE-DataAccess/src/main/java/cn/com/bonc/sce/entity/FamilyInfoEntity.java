package cn.com.bonc.sce.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "V_FAMILY_INFO",schema = "STARCLOUDPORTAL")
public class FamilyInfoEntity {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private String USER_ID;

    @Column(name = "USER_NAME")
    private String USER_NAME;

    @Column(name = "USER_ACCOUNT")
    private String USER_ACCOUNT;

    @Column(name = "FAMILY_ROLE")
    private String FAMILY_ROLE;

    @Column(name = "STUDENT_NAME")
    private String STUDENT_NAME;
}
