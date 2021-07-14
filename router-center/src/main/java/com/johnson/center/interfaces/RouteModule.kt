package com.johnson.center.interfaces

import android.content.Context

interface RouteModule {
    /**
     *  初始化组件
     */
    fun init(context: Context)

    /**
     * 移除组件
     */
    fun unInit()
}