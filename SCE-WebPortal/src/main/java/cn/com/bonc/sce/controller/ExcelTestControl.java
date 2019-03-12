package cn.com.bonc.sce.controller;

/**
 * @author jc_D
 * @description
 * @date 2019/3/1
 **/

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.com.bonc.sce.model.ExcelToUser;
import cn.com.bonc.sce.tool.ParseExcel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel 导出示例 demo
 */
@RestController
@RequestMapping( "/export-excel" )
public class ExcelTestControl {
    /**
     * 数据为map的情况
     *
     * @param request
     * @param httpServletResponse
     */
    @GetMapping( "/map" )
    public void exporUserFromEntity( HttpServletRequest request, HttpServletResponse httpServletResponse ) {
        //造几个List< Map< String, Object > > 假数据
        Map map = new HashMap();
        map.put( "name", "张三" );
        map.put( "age", 11 );
        map.put( "birth", new Date() );
        Map map1 = new HashMap();
        map1.put( "name", "李四" );
        map1.put( "age", 12 );
        map1.put( "birth", new Date() );
        Map map2 = new HashMap();
        map2.put( "name", "王二" );
        map2.put( "age", 13 );
        map2.put( "birth", new Date() );
        List< Map< String, Object > > list = new ArrayList<>();
        list.add( map );
        list.add( map1 );
        list.add( map2 );

        //
        List< ExcelExportEntity > entity = new ArrayList<>();
        entity.add( new ExcelExportEntity( "姓名", "name" ) );
        entity.add( new ExcelExportEntity( "年龄", "age" ) );
        ExcelExportEntity r2 = new ExcelExportEntity( "出生日期", "birth", 50 );
        r2.setFormat( "yyyy年MM月dd日" );
        entity.add( r2 );

        Workbook workbook = null;
        ExportParams exportParms = new ExportParams( "标题", "Sheet1" );

        //不固定表头
        exportParms.setFixedTitle( false );
        //导出
        workbook = ExcelExportUtil.exportBigExcel( exportParms, entity, list );
        ExcelExportUtil.closeExportBigExcel();
        //下载
        ParseExcel.downLoadExcel( "用户信息.xlsx", httpServletResponse, workbook );
    }

    /**
     * 数据为实体类
     */
    @GetMapping( "/entity" )
    public void exporUserFromMap( HttpServletResponse httpServletResponse ) {
        //1.提供需要导出的数据
        ExcelToUser excelToUser = new ExcelToUser();
        excelToUser.setUserName( "张3" );
        excelToUser.setBirthDate( new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss" ).format( new Date() ) );
        excelToUser.setGender( "1" );
        List< ExcelToUser > userList = new ArrayList<>();
        userList.add( excelToUser );
        //2.导出，下载
        ParseExcel.exportExcel( userList, "标题", "sheet1", ExcelToUser.class, "文件名.xls", httpServletResponse );

    }
}
