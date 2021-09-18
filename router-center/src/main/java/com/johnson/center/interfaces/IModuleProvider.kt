package com.johnson.center.interfaces

import com.johnson.center.bean.MethodParam
import com.johnson.center.bean.MethodResult

interface IModuleProvider : RouteModule {
    /**
     * 外部调用组件方法
     */
    fun invokeMethod(method: String, mutableList: MutableList<MethodParam>? = null): MethodResult

    fun getModuleKey(): String


}