package com.johnson.test_moduleb

import com.johnson.annotation.ProvideMethod


/**
 * @Author: Johnson
 * @CreateDate: 2021/7/14 20:49
 * @Description:
 */
open class ProjectBProvide {
    @ProvideMethod
    fun getBInt() = 20

    @ProvideMethod
    fun getBString(string: String?, int: Int) = string + int
}