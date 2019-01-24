package cn.com.bonc.sce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * banner
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class Authority {
    private String authorityName;
    private Integer pid;
    private Integer district;
    private Integer city;
    private Integer province;
    private Integer county;
    private Integer address;
    private Integer telePhone;

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName( String authorityName ) {
        this.authorityName = authorityName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid( Integer pid ) {
        this.pid = pid;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict( Integer district ) {
        this.district = district;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity( Integer city ) {
        this.city = city;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince( Integer province ) {
        this.province = province;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty( Integer county ) {
        this.county = county;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress( Integer address ) {
        this.address = address;
    }

    public Integer getTelePhone() {
        return telePhone;
    }

    public void setTelePhone( Integer telePhone ) {
        this.telePhone = telePhone;
    }
}
