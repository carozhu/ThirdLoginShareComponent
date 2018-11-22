package com.carozhu;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * 生成微信登录和分享所必须要写的代码，可以直接跳过微信包名限制
 */

public class WXEntryVisitor extends SimpleAnnotationValueVisitor7{

    private Filer mFiler;
    private String mPackageName;

    WXEntryVisitor(Filer filer){
        mFiler = filer;
    }

    @Override
    public Object visitString(String s, Object o) {
        mPackageName = s;
        return o;
    }

    @Override
    public Object visitType(TypeMirror typeMirror, Object o) {
        generateJavaCode(typeMirror);
        return o;
    }

    //生成java代码
    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .superclass(TypeName.get(typeMirror))
                .build();

        JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信登录和分享入口文件")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
