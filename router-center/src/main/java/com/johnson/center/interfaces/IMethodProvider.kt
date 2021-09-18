package com.johnson.center.interfaces

import kotlin.reflect.KClass

/**
 * @Author: Johnson
 * @CreateDate: 2021/9/14 0:24
 * @Description:
 */
interface IMethodProvider {

    @Throws(Exception::class)
    fun regMethod(method: String, parameters: MutableList<Class<*>>?)
}