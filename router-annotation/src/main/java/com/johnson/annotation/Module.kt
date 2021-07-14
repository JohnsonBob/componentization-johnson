package com.johnson.annotation

import kotlin.reflect.KClass


/**
 * @Author: Johnson
 * @CreateDate: 2021/7/14 21:51
 * @Description:
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class Module(val provide: KClass<*>)
