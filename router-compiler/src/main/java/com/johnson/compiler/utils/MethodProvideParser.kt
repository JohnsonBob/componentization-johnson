package com.johnson.compiler.utils

import com.johnson.compiler.bean.ProvideMethodBean
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.util.ArrayList
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

        generatePropertyList(provider)
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

    private fun generatePropertyList(provider: TypeSpec.Builder) {
        val methodProvide = PropertySpec.builder(Constants.PROPERTY_METHOD_PROVIDE,
            ClassTypeUtils.RouterMethodProvider.copy(nullable = true), KModifier.PRIVATE).mutable().initializer("null").build()
        provider.addProperty(methodProvide)

        val moduleProvider = PropertySpec.builder(Constants.PROPERTY_MODULE_PROVIDER,
            ("$packageName.$className").className.copy(nullable = true), KModifier.PRIVATE).mutable().initializer("null").build()
        provider.addProperty(moduleProvider)
    }

    private fun generateInvokeMethod(method: ProvideMethodBean): FunSpec {
        val invoke = FunSpec.builder(Constants.METHOD_INVOKE + method.methodName)
            .returns(method.returnType.autoAsTypeName().copy(true))

        val mutableList = ArrayList::class.className
        val mutableListOfAny = mutableList.parameterizedBy(Any::class.className)
        invoke.addParameter("params", mutableListOfAny)
        val parameters = arrayListOf<String>()
        method.parameters.forEachIndexed { index, item ->
            invoke.addStatement("val ${Constants.METHOD_PARAM + index} = params.getOrNull($index) as? %T", item.autoAsTypeName())
            parameters.add(Constants.METHOD_PARAM + index)
        }

        val invokeString = StringBuilder()
        invokeString.append("return ${Constants.PROPERTY_MODULE_PROVIDER}?.${method.methodName}(")
        parameters.joinTo(invokeString, ", ")
        invokeString.append(")")
        invoke.addStatement(invokeString.toString())
        return invoke.build()
    }

    private fun generateRegMethod(method: ProvideMethodBean): FunSpec {
        val register = FunSpec.builder(Constants.METHOD_REG + method.methodName)
        val mutableList = ArrayList::class.className
        register.addStatement("val result = %T<Class<*>>()", mutableList)
        method.parameters.forEach {
            register.addStatement("result.add(%T::class.java) ", it.autoAsTypeName())
        }
        register.addStatement("${Constants.PROPERTY_METHOD_PROVIDE}?.regMethod(%S, %N)", method.methodName, "result")
        return register.build()
    }
}