package cn.com.bonc.sce.model;

/**
 * 应用分类
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
public class AppClass {
    private Integer id;
    private String typeName;
    private String remarks;
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName( String typeName ) {
        this.typeName = typeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks( String remarks ) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }

    public AppClass() {

    }

    public AppClass( Integer id, String typeName, String remarks, Integer isDelete ) {

        this.id = id;
        this.typeName = typeName;
        this.remarks = remarks;
        this.isDelete = isDelete;
    }
}
