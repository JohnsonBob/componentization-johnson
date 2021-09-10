package com.johnson.compiler.utils

import com.squareup.kotlinpoet.ClassName

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/29 19:46
 * @Description:
 */
object ClassTypeUtils {
    val MethodParam = ClassName.bestGuess("com.johnson.center.bean.MethodParam")
    val MethodResult = ClassName.bestGuess("com.johnson.center.bean.MethodResult")
    val ModuleProviderInterface = ClassName.bestGuess("com.johnson.center.interfaces.ModuleProviderInterface")
    val RouteModule = ClassName.bestGuess("com.johnson.center.interfaces.RouteModule")
}