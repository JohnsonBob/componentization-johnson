package com.johnson.center.bean

import com.johnson.center.annotation.InvokeType

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/13 23:24
 * @Description: 调用后的结果
 */
data class MethodResult(
    @InvokeType var code: Int = InvokeType.NOT_FOUND,
    var result: Any? = null,
)