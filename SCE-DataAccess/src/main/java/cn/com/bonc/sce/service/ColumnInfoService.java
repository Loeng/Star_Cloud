package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ColumnInfoDao;
import cn.com.bonc.sce.entity.ColumnInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 栏目接口
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/26 15:00
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class ColumnInfoService {
    @Autowired
    private ColumnInfoDao columnInfoDao;

    /**
     * 添加栏目
     *
     * @param columnInfo 添加栏目
     * @return 是否添加成功
     */
    public RestRecord insert( ColumnInfo columnInfo ) {
        columnInfo.setId( null );
        columnInfo.setIsDelete( 1 );
        return new RestRecord( 200,columnInfoDao.save( columnInfo ));
    }

    /**
     * 通过id删除
     *
     * @param id id
     * @return 删除是否成功
     */
    public RestRecord deleteById( Integer id ) {
        return new RestRecord( 200,columnInfoDao.updateDeleteStatusById( id ));
    }

    /**
     * 更新栏目
     *
     * @param columnInfo 栏目
     * @return columnInfo
     */
    public RestRecord updateInfo( ColumnInfo columnInfo ) {
        return new RestRecord( 200,columnInfoDao.save( columnInfo ));
    }

    /**
     * 获取所有栏目数据
     *
     * @return 栏目数据list
     */
    public RestRecord getAll() {
        return new RestRecord( 200,columnInfoDao.findByIsDelete(1));
    }
}
