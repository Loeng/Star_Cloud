package cn.com.bonc.sce.annotation;

import java.lang.annotation.*;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/29 16:19
 */
@Target( ElementType.PARAMETER )
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Inherited
public @interface Payloads {
}
