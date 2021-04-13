package com.johnson.compiler

import com.google.auto.service.AutoService
import com.johnson.annotation.CompilerAnnotation
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class InterfaceProcessor : AbstractProcessor() {


    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return Collections.singleton(CompilerAnnotation::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        val init = MethodSpec.methodBuilder("init")
            .addModifiers(Modifier.PUBLIC)
            .returns(Void.TYPE)
            .build()

        val destroy = MethodSpec.methodBuilder("destroy")
            .addModifiers(Modifier.PUBLIC)
            .returns(Void.TYPE)
            .build()

        val elementsAnnotatedWith =
            roundEnv?.getElementsAnnotatedWith(CompilerAnnotation::class.java) ?: return false

        elementsAnnotatedWith.forEach { element ->
            val typeElement = element as TypeElement
            val interfaceName = ClassName.get(typeElement)
            val packageName = interfaceName.packageName()

            val build = TypeSpec.classBuilder(element.simpleName.toString() + "Impl")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethods(arrayListOf(init, destroy))
                .superclass(interfaceName)
                .build()


            try {
                val javaFile = JavaFile.builder(packageName, build)
                    .addFileComment(" This codes are generated automatically. Do not modify!")
                    .build()
                javaFile.writeTo(processingEnv.filer)
            } catch (e: Exception) {
                return false
            }
        }
        return true
    }
}
