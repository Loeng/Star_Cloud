package cn.com.bonc.sce.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suzhenyu on 2019/4/26.
 */
public class ReportApproveBean {


    private List< String > idList = new ArrayList<String>();
    private String approveStatus;
    private String approveUserId;
    private String approveRemark;

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveUserId() {
        return approveUserId;
    }

    public void setApproveUserId(String approveUserId) {
        this.approveUserId = approveUserId;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }
}
