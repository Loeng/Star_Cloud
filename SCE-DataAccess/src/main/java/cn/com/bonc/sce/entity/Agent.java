package cn.com.bonc.sce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author BTW
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "SCE_ENTITY_AGENT", schema = "STARCLOUDPORTAL" )
public class Agent implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private long agentId;

    @Column( name = "AGENT_NAME" )
    private String agentName;

    @Column( name = "GRADE" )
    private String grade;

    @Column( name = "AGENT_AREA" )
    private String agentArea;

    @Column( name = "AGENT_ADDRESS" )
    private String agentAddress;

    @Column( name = "AGENT_TAX_NUM" )
    private String agentTaxNum;

    @Column( name = "JURIDICAL_PERSON" )
    private String juridicalPerson;

    @Column( name = "AGENT_WEBSITE" )
    private String agentWebsite;

    @Column( name = "AGENT_REGISTATION_ID" )
    private String agentRegistationId;

    @Column( name = "AGENT_EMAIL" )
    private String agentEmail;

    @Column( name = "ESTABLISHING_TIME" )
    private Timestamp establishingTime;

    @Column( name = "AGENT_INTRODUCTION" )
    private String agentIntroduction;

    @Column( name = "IS_DELETE" )
    private long isDelete = 1L;
}
