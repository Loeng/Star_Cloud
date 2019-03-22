package cn.com.bonc.sce.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 学生家长关系
 *
 * @author jc_D
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentParentRel {
    private Long id;
    private String parentUserId;
    private String studentUserId;
    private Integer isMain;

}
