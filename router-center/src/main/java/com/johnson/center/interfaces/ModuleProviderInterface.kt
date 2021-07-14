package com.johnson.center.interfaces

import com.johnson.center.bean.MethodParam
import com.johnson.center.bean.MethodResult

interface ModuleProviderInterface : RouteModule {
    /**
     * 外部调用组件方法
     */
    fun invokeMethod(method: String, mutableList: List<MethodParam>? = null): MethodResult

    fun getModuleKey():String
}