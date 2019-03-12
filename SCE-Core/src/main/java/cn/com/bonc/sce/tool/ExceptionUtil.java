package cn.com.bonc.sce.tool;

/**
 * created by 李啸峰 on 2018/5/14.
 * 2018 @ cmp
 * @lastUpdate 2018/5/30 by Leucippus 添加了针对 stacktrace 的方法。
 */
public class ExceptionUtil {

    public static String getStackTrace( Exception e ) {
        StringBuilder sb = new StringBuilder( e.getClass().getName() ).append( e.getMessage() ).append( "\n" );
        for ( StackTraceElement stackTraceElement : e.getStackTrace() ) {
            sb.append( stackTraceElement.getClassName() ).append( "." ).append( stackTraceElement.getMethodName() )
                    .append( "(" ).append( stackTraceElement.getFileName() ).append( ":" ).append( stackTraceElement.getLineNumber() ).append( ")\n" );
        }
        return sb.toString();
    }

    public static String getStackTraceFlatString( StackTraceElement[] stackTraceElements ) {
        StringBuilder sb = new StringBuilder().append( "\n" );
        for ( StackTraceElement stackTraceElement : stackTraceElements ) {
            sb.append( stackTraceElement.getClassName() ).append( "." ).append( stackTraceElement.getMethodName() )
                    .append( "(" ).append( stackTraceElement.getFileName() ).append( ":" ).append( stackTraceElement.getLineNumber() ).append( ")\n" );
        }
        return sb.toString();
    }

    public static String string2Json( String s ) {
        return  s;
//        StringBuffer sb = new StringBuffer();
//        for ( int i = 0; i < s.length(); i++ ) {
//
//            char c = s.charAt( i );
//            switch ( c ) {
//                case '\"':
//                    sb.append( "\\\"" );
//                    break;
//                case '\\':
//                    sb.append( "\\\\" );
//                    break;
//                case '/':
//                    sb.append( "\\/" );
//                    break;
//                case '\b':
//                    sb.append( "\\b" );
//                    break;
//                case '\f':
//                    sb.append( "\\f" );
//                    break;
//                case '\n':
//                    sb.append( "\\n" );
//                    break;
//                case '\r':
//                    sb.append( "\\r" );
//                    break;
//                case '\t':
//                    sb.append( "\\t" );
//                    break;
//                default:
//                    sb.append( c );
//            }
//        }
//        return sb.toString();
    }
}
