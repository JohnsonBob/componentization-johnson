package com.johnson.compiler.bean

import com.johnson.annotation.ProvideMethod
import com.johnson.compiler.utils.isNullable
import javax.lang.model.element.ExecutableElement
import javax.lang.model.type.TypeMirror

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/21 11:42
 * @Description:
 */
class ProvideMethodBean constructor(private val element: ExecutableElement) {
    var methodName: String = ""
    var returnType: TypeMirror
    var parameters: MutableList<Parameter> = mutableListOf()

    init {
        val annotation = element.getAnnotation(ProvideMethod::class.java)
        methodName = annotation.customName
        if (methodName.isEmpty()) {
            methodName = element.simpleName.toString()
        }

        returnType = element.returnType

        element.parameters?.forEach {
            parameters.add(Parameter(it.asType(), it.isNullable))
        }
    }
}