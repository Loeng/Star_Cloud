package cn.com.bonc.sce.model;

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
    private String district;
    private String city;
    private String province;
    private String county;
    private String address;
    private String telePhone;

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

    public String getDistrict() {
        return district;
    }

    public void setDistrict( String district ) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince( String province ) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty( String county ) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone( String telePhone ) {
        this.telePhone = telePhone;
    }
}
