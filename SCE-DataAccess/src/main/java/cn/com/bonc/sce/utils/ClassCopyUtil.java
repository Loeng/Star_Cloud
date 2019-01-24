package cn.com.bonc.sce.utils;

import cn.com.bonc.sce.entity.MarketAppVersion;
import cn.com.bonc.sce.model.AppVersionModel;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * JAVA对象属性复制
 *
 * @author jc_D
 * @description
 * @date 2019/1/24
 **/
public class ClassCopyUtil {
    public static void Copy( Object source, Object dest ) throws Exception {
        //获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo( source.getClass(), java.lang.Object.class );
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo( dest.getClass(), java.lang.Object.class );
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try {
            for ( int i = 0; i < sourceProperty.length; i++ ) {

                for ( int j = 0; j < destProperty.length; j++ ) {
                    //源属性与目标属性相同且目标属性值为null才进行属性复制
                    if ( sourceProperty[ i ].getName().equals( destProperty[ j ].getName() ) && destProperty[ j ].getReadMethod().invoke( dest, new Object[] {} ) == null ) {
                        //调用source的getter方法和dest的setter方法

                        destProperty[ j ].getWriteMethod().invoke( dest, sourceProperty[ i ].getReadMethod().invoke( source ) );
                        break;
                    }
                }
            }
        } catch ( Exception e ) {
            throw new Exception( "属性复制失败:" + e.getMessage() );
        }
    }

    public static void main( String[] args ) {
        MarketAppVersion source = new MarketAppVersion();
        source.setAppVersion( "***version*******" );
        source.setMd5Code( "md5" );
        source.setIsDelete( 1L );
        AppVersionModel mod2 = new AppVersionModel();
        mod2.setAppId( "appid111" );
        mod2.setAppVersion( "mod2" );
        System.out.println( "-----" );
        System.out.println( source.toString() );
        System.out.println( "-----" );
        System.out.println( mod2.toString() );

        try {
            Copy( source, mod2 );
            System.out.println( "-----" );
            System.out.println( source.toString() );
            System.out.println( "-----" );
            System.out.println( mod2.toString() );
        } catch ( Exception e ) {
            System.out.println( e );
        }


    }
}
