package com.carozhu;

/**
 * Author: carozhu
 * Date  : On 2018/8/3
 * Desc  :
 * 参考   https://github.com/liaowjcoder/AnnotationProcessingToolDemo/
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface WXEntryAnnotation {

    /**
     * 标识我们WXEntrayActivity所在的包名
     *
     * 最后会生成 packageName().wxapi.WXEntryActivity 这么一个类。
     * @return 返回包名
     */
    String packageName();


    /**
     * 表示 WXEntrayActivity 需要继承的那个类的字节码文件，例如我们需要将生成的 WXEntrayActivity 去继承 WXDelegateEntryActivity 那么这个 superClass 返回的就是 WXDelegateEntryActivity 的字节码文件对象。
     * @return
     */
    Class<?> superClass();

}