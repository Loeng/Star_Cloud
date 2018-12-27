package cn.com.bonc.sce.tool;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/22 15:29
 */
public class FileUploadUtil {

    public static String getFileSuffix ( String fileName ) {
        int suffixIndex = fileName.lastIndexOf( "." );
        if ( suffixIndex == -1 ) {
            return "";
        } else {
            return fileName.substring( fileName.lastIndexOf(".") );
        }
    }

    public static boolean uploadFile ( byte[] bytes, String saveFileName, String fileSavePath ) {
        Path path = Paths.get( fileSavePath, saveFileName );
        File targetFile = new File( path.toAbsolutePath().toString() );
        if ( !targetFile.getParentFile().exists() ) {
            targetFile.getParentFile().mkdirs();
        }

        try ( BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile)) ) {
            out.write( bytes );
            out.flush();
            return true;
        } catch ( IOException e ) {
            return false;
        }
    }

}
