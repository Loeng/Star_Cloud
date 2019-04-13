package cn.com.bonc.sce.service;

import cn.com.bonc.sce.mapper.AppStateListMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * app运营状态列表
 */
@Slf4j
@Service
public class AppStateListService {
    @Autowired
    private AppStateListMapper appStateListMapper;

    /**
     * 查询厂商id
     *
     * @return
     */
    public Long getCompanyIdByUid( String uid ) {
        return appStateListMapper.getCompanyIdByUid( uid );
    }

    /**
     * 运营中
     *
     * @param auditStatus
     * @param typeId
     * @param keyword
     * @param orderBy
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    public PageInfo running( String auditStatus, Integer typeId, String keyword, String orderBy, Integer pageNum, Integer pageSize, String userId ) {
        Long companyId = getCompanyIdByUid( userId );
        String state = "('4')";
        PageHelper.startPage( pageNum, pageSize );
        List< Map > agentList = appStateListMapper.getListByCondition( companyId, keyword, typeId, state );
        PageInfo pageInfo = new PageInfo( agentList );
        return pageInfo;
    }

    public PageInfo onShelfAudit( String auditStatus, Integer typeId, String keyword, String orderBy, Integer pageNum, Integer pageSize, String userId ) {
        Long companyId = getCompanyIdByUid( userId );
        String state = "('1','3')";
        if ( "1".equals( auditStatus ) ) {
            state = "('1')";
        }
        if ( "2".equals( auditStatus ) ) {
            state = "('3')";
        }
        PageHelper.startPage( pageNum, pageSize );
        List< Map > agentList = appStateListMapper.getListByCondition( companyId, keyword, typeId, state );
        PageInfo pageInfo = new PageInfo( agentList );
        return pageInfo;
    }

    public PageInfo iterationAudit( String auditStatus, Integer typeId, String keyword, String orderBy, Integer pageNum, Integer pageSize, String userId ) {
        Long companyId = getCompanyIdByUid( userId );
        String state = "('2','6')";
        if ( "1".equals( auditStatus ) ) {
            state = "('2')";
        }
        if ( "2".equals( auditStatus ) ) {
            state = "('6')";
        }
        PageHelper.startPage( pageNum, pageSize );
        List< Map > agentList = appStateListMapper.getListByCondition( companyId, keyword, typeId, state );
        PageInfo pageInfo = new PageInfo( agentList );
        return pageInfo;
    }

    public PageInfo downShelfAudit( String auditStatus, Integer typeId, String keyword, String orderBy, Integer pageNum, Integer pageSize, String userId ) {
        Long companyId = getCompanyIdByUid( userId );
        String state = "('9','10')";
        if ( "1".equals( auditStatus ) ) {
            state = "('9')";
        }
        if ( "2".equals( auditStatus ) ) {
            state = "('10')";
        }
        PageHelper.startPage( pageNum, pageSize );
        List< Map > agentList = appStateListMapper.getListByCondition( companyId, keyword, typeId, state );
        PageInfo pageInfo = new PageInfo( agentList );
        return pageInfo;
    }

    public PageInfo alreadyDown( String auditStatus, Integer typeId, String keyword, String orderBy, Integer pageNum, Integer pageSize, String userId ) {
        Long companyId = getCompanyIdByUid( userId );
        String state = "('5','7')";
        if ( "1".equals( auditStatus ) ) {
            state = "('5')";
        }
        if ( "2".equals( auditStatus ) ) {
            state = "('7')";
        }
        PageHelper.startPage( pageNum, pageSize );
        List< Map > agentList = appStateListMapper.getListByCondition( companyId, keyword, typeId, state );
        PageInfo pageInfo = new PageInfo( agentList );
        return pageInfo;
    }
}
