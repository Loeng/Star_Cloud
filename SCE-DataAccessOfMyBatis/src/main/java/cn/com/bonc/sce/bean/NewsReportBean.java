package cn.com.bonc.sce.bean;

import java.util.Date;

/**
 * Created by suzhenyu on 2019/4/24.
 */
public class NewsReportBean {
    private Long reportId;
    private Long contentId;
    private Long reportType;
    private String reportDesc;
    private Long picId1;
    private Long picId2;
    private Long picId3;
    private String picUrl1;
    private String picUrl2;
    private String picUrl3;
    private String reportUserId;
    private Date reportDate;
    private String reportIpAddr;
    private String reportBrower;
    private Long reportStatus;
    private Long approveStatus;
    private String approveRemark;
    private Date approveDate;
    private String approveUserId;
    private Date createTime;
    private Date updateTime;
    private String createUserId;
    private String updateUserId;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getReportType() {
        return reportType;
    }

    public void setReportType(Long reportType) {
        this.reportType = reportType;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public Long getPicId1() {
        return picId1;
    }

    public void setPicId1(Long picId1) {
        this.picId1 = picId1;
    }

    public Long getPicId2() {
        return picId2;
    }

    public void setPicId2(Long picId2) {
        this.picId2 = picId2;
    }

    public Long getPicId3() {
        return picId3;
    }

    public void setPicId3(Long picId3) {
        this.picId3 = picId3;
    }

    public String getPicUrl1() {
        return picUrl1;
    }

    public void setPicUrl1(String picUrl1) {
        this.picUrl1 = picUrl1;
    }

    public String getPicUrl2() {
        return picUrl2;
    }

    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    public String getPicUrl3() {
        return picUrl3;
    }

    public void setPicUrl3(String picUrl3) {
        this.picUrl3 = picUrl3;
    }

    public String getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(String reportUserId) {
        this.reportUserId = reportUserId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportIpAddr() {
        return reportIpAddr;
    }

    public void setReportIpAddr(String reportIpAddr) {
        this.reportIpAddr = reportIpAddr;
    }

    public String getReportBrower() {
        return reportBrower;
    }

    public void setReportBrower(String reportBrower) {
        this.reportBrower = reportBrower;
    }

    public Long getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Long reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Long getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Long approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }
}
