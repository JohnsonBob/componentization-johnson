package com.johnson.compiler.utils

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import javax.lang.model.type.TypeMirror
import kotlin.jvm.internal.ClassBasedDeclarationContainer
import kotlin.reflect.KClass
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/29 19:46
 * @Description:
 */
object ClassTypeUtils {
    val MethodParam = ClassName.bestGuess("com.johnson.center.bean.MethodParam")
    val MethodResult = ClassName.bestGuess("com.johnson.center.bean.MethodResult")
    val IModuleProvider = ClassName.bestGuess("com.johnson.center.interfaces.IModuleProvider")
    val RouteModule = ClassName.bestGuess("com.johnson.center.interfaces.RouteModule")
    val IMethodProvider = ClassName.bestGuess("com.johnson.center.interfaces.IMethodProvider")
    val RouterMethodProvider = ClassName.bestGuess("com.johnson.center.provider.RouterMethodProvider")


}

/**
 * 获取需要把java类型映射成kotlin类型的ClassName  如：java.lang.String 在kotlin中的类型为kotlin.String 如果是空则表示该类型无需进行映射
 */
fun TypeMirror.autoAsTypeName(): TypeName {
    val className =
        JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(FqName(this.asTypeName().toString()))?.asSingleFqName()?.asString()
    return if (className == null) {
        this.asTypeName()
    } else {
        ClassName.bestGuess(className)
    }
}

val <T : Any> KClass<T>.className: ClassName
    get() {
        val className =
            JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(FqName(this.asTypeName().toString()))?.asSingleFqName()?.asString()
        return if (className == null) {
            this.asTypeName()
        } else {
            ClassName.bestGuess(className)
        }
    }

val String.className: ClassName
    get() {
        val className =
            JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(FqName(this))?.asSingleFqName()?.asString()
        return if (className == null) {
            ClassName.bestGuess(this)
        } else {
            ClassName.bestGuess(className)
        }
    }