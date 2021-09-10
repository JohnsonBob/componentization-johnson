package com.johnson.compiler

import com.google.auto.service.AutoService
import com.johnson.annotation.ProvideMethod
import com.johnson.compiler.bean.ProvideMethodBean
import com.johnson.compiler.utils.MethodProvideParser
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class InterfaceProcessor : AbstractProcessor() {


    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return Collections.singleton(ProvideMethod::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?,
    ): Boolean {
        if (roundEnv == null) return false

        generateMethodProvide(roundEnv)
        generaModuleProxy(roundEnv)
        return true
    }

    private fun generateMethodProvide(roundEnv: RoundEnvironment) {
        val elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(ProvideMethod::class.java)
        var methodParser: MethodProvideParser? = null

        elementsAnnotatedWith.forEach { element ->
            if (methodParser == null) {
                val enclosingElement = element.enclosingElement as? TypeElement
                if (enclosingElement != null)
                    methodParser = MethodProvideParser(enclosingElement)
            }

            (element as? ExecutableElement)?.run {
                methodParser?.addMethod(ProvideMethodBean(this))
            }
        }

        methodParser?.generateFile(processingEnv.filer)
    }

    private fun generaModuleProxy(roundEnv: RoundEnvironment) {

    }
}
