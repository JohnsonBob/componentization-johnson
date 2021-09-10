package com.johnson.test_moduleb

import android.content.Context
import com.johnson.annotation.Module
import com.johnson.center.interfaces.RouteModule

/**
 * @Author: Johnson
 * @CreateDate: 2021/7/14 20:49
 * @Description:
 */
@Module(provide = ProjectBProvide::class)
class ProjectBModule : RouteModule {
    override fun init(context: Context) {
    }

    override fun unInit() {
    }

}