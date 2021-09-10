package com.johnson.compiler.utils

import com.johnson.compiler.bean.ProvideMethodBean
import com.squareup.kotlinpoet.*
import javax.annotation.processing.Filer
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/28 19:20
 * @Description: 方法提供者解析类
 */
class MethodProvideParser constructor(element: TypeElement, elementUtils: Elements? = null) {
    private var packageName: String = ""
    private val methodList: MutableList<ProvideMethodBean> = mutableListOf()
    private var className = ""

    init {
        val asClassName = element.asClassName()
        packageName = asClassName.packageName
        className = asClassName.simpleName
    }

    fun addMethod(method: ProvideMethodBean) {
        methodList.add(method)
    }

    fun generateFile(file: Filer) {
        val provider = TypeSpec.classBuilder(className + Constants.METHOD_PROVIDER)
        methodList.forEach { method ->

            val invoke = generateInvokeMethod(method)
            val register = generateRegMethod(method)
            provider.addFunction(register).addFunction(invoke)
        }
        val kotlinFile = FileSpec.builder(packageName, className + Constants.METHOD_PROVIDER)
            .addType(provider.build())
            .build()

        kotlinFile.writeTo(file)
    }

    private fun generateInvokeMethod(method: ProvideMethodBean): FunSpec {
        val invoke = FunSpec.builder(Constants.METHOD_INVOKE + method.methodName)
            .returns(method.returnType.asTypeName())
        method.parameters.forEachIndexed { index, item ->
            invoke.addParameter(Constants.METHOD_PARAM + index, item.asTypeName())
        }
        return invoke.build()
    }

    private fun generateRegMethod(method: ProvideMethodBean): FunSpec {
        val register = FunSpec.builder(Constants.METHOD_REG + method.methodName)

        return register.build()
    }
}