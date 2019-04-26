package cn.com.bonc.sce.model;

import java.util.Date;

/**
 * @author szy
 */
public class NewsReportModel {

    private String reportId;
    private String contentId;
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

    private String startDate;
    private String endDate;
    private String contentTitle;


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
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
