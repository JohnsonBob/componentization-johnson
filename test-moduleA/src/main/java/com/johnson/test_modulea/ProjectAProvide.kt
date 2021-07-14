package com.johnson.test_modulea

import com.johnson.annotation.ProvideMethod


/**
 * @Author: Johnson
 * @CreateDate: 2021/7/14 20:49
 * @Description:
 */
class ProjectAProvide {
    @ProvideMethod
    fun getAInt() = 15

}