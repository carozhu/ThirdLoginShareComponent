package com.carozhu;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

@SuppressWarnings("unused")
@AutoService(Processor.class)
public class ComponentProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotiations = new LinkedHashSet<>();
        annotiations.add(WXEntryAnnotation.class.getCanonicalName());
        //使用WXEntryAnnotation 等价 WXPayAnnotation 也是可以的。这里为了Simple
        annotiations.add(WXPayAnnotation.class.getCanonicalName());
        return annotiations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generWXEntryActivityCode(roundEnvironment);
        generWXPayEntryActivityCode(roundEnvironment);
        return true;
    }

    /**
     * 生成微信登录分享code
     */
    private void generWXEntryActivityCode(RoundEnvironment roundEnvironment) {
        WXEntryVisitor wxEntryVisitor = new WXEntryVisitor(processingEnv.getFiler());
        scan(roundEnvironment, WXEntryAnnotation.class, wxEntryVisitor);
    }

    /**
     * 生成微信登录分享code
     */
    private void generWXPayEntryActivityCode(RoundEnvironment roundEnvironment) {
        WXPayVisitor wxPayVisitor = new WXPayVisitor(processingEnv.getFiler());
        scan(roundEnvironment, WXPayAnnotation.class, wxPayVisitor);
    }


    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation, AnnotationValueVisitor visitor) {
        //返回使用给定注释类型注释的元素。
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            //getAnnotationMirrors  返回直接存在于此元素上的注释。
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : annotationMirrors) {
                //AnnotationMirror  返回此注释元素的值。
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                    //将一个 visitor 应用到此值。
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }
}
