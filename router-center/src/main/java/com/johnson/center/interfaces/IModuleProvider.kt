package com.johnson.center.interfaces

import com.johnson.center.bean.MethodResult

interface IModuleProvider : RouteModule {
    /**
     * 外部调用组件方法
     */
    fun invokeMethod(method: String, mutableList: MutableList<Any>? = null): MethodResult

    fun getModuleKey(): String


}