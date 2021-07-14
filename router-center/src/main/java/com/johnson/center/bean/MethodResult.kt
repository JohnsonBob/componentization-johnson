package com.johnson.center.bean

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/13 23:24
 * @Description: 调用后的结果
 */
data class MethodResult(
    var code: Int = 404,
    var result: MethodParam
)