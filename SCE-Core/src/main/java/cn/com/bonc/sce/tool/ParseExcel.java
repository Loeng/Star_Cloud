package cn.com.bonc.sce.tool;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/26 16:38
 * @Description:
 */
public class ParseExcel {

    private  final   static Logger log = LoggerFactory.getLogger( ParseExcel.class );

    public static void exportExcel( List< ? > list, String title, String sheetName, Class< ? > pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response ) {
        ExportParams exportParams = new ExportParams( title, sheetName );
        exportParams.setCreateHeadRows( isCreateHeader );
        defaultExport( list, pojoClass, fileName, response, exportParams );

    }

    public static void exportExcel( List< ? > list, String title, String sheetName, Class< ? > pojoClass, String fileName, HttpServletResponse response ) {
        defaultExport( list, pojoClass, fileName, response, new ExportParams( title, sheetName ) );
    }

    public static void exportExcel( List< Map< String, Object > > list, String fileName, HttpServletResponse response ) {
        defaultExport( list, fileName, response );
    }

    private static void defaultExport( List< ? > list, Class< ? > pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams ) {
        Workbook workbook = ExcelExportUtil.exportExcel( exportParams, pojoClass, list );
        if ( workbook != null ) {
            ;
        }
        downLoadExcel( fileName, response, workbook );
    }

    private static void downLoadExcel( String fileName, HttpServletResponse response, Workbook workbook ) {
        try {
            response.setCharacterEncoding( "UTF-8" );
            response.setHeader( "content-Type", "application/vnd.ms-excel" );
            response.setHeader( "Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode( fileName, "UTF-8" ) );
            workbook.write( response.getOutputStream() );
        } catch ( IOException e ) {
              log.error( e.getMessage() );
        }
    }

    private static void defaultExport( List< Map< String, Object > > list, String fileName, HttpServletResponse response ) {
        Workbook workbook = ExcelExportUtil.exportExcel( list, ExcelType.HSSF );
        if ( workbook != null ) {
            ;
        }
        downLoadExcel( fileName, response, workbook );
    }

    public static < T > List< T > importExcel( String filePath, Integer titleRows, Integer headerRows, Class< T > pojoClass ) {
        if ( StringUtils.isBlank( filePath ) ) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows( titleRows );
        params.setHeadRows( headerRows );
        List< T > list = null;
        try {
            list = ExcelImportUtil.importExcel( new File( filePath ), pojoClass, params );
        } catch ( NoSuchElementException e ) {
            log.error( "模板不能为空"  );
        } catch ( Exception e ) {
            e.printStackTrace();
            log.error( e.getMessage() );
        }
        return list;
    }


    /**
     * 针对上传文件解析
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static < T > List< T > importExcel( MultipartFile file, Integer titleRows, Integer headerRows, Class< T > pojoClass ) {
        if ( file == null ) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows( titleRows );
        params.setHeadRows( headerRows );
        List< T > list = null;
        try {
            list = ExcelImportUtil.importExcel( file.getInputStream(), pojoClass, params );
        } catch ( NoSuchElementException e ) {
            log.error( "excel文件不能为空" );
        } catch ( Exception e ) {
            log.error( e.getMessage() );
        }
        return list;
    }

}
