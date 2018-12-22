package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.ParentsOperationDao;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 家长操作接口
 */
@RestController
@RequestMapping( "/parentsOperation" )
public class ParentsOperationApiController {
    @Autowired
    private ParentsOperationDao parentsOperationDao;

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @return 添加结果
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertParentsInfo( ParentsInfo parentsInfo ){
        int total = 0;
        total += parentsOperationDao.insertParentsAccountInfo( parentsInfo );
        total += parentsOperationDao.insertParentsDataInfo( parentsInfo );
        total += parentsOperationDao.insertParentsStudentRelInfo( parentsInfo );
        try{
            if(total>2) {
                return new RestRecord(200,total);
            } else {
                return new RestRecord(200,"error");
            }
        }catch ( Exception e ){
            return new RestRecord(500,"", e);
        }
    }
}
