package com.johnson.center.annotation

import androidx.annotation.IntDef

/**
 * @Author: Johnson
 * @CreateDate: 2021/9/24 11:04
 * @Description:
 */


@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
@IntDef(
    InvokeType.SUCCESS,
    InvokeType.BAD_REQUEST,
    InvokeType.NOT_FOUND,
    InvokeType.EXCEPTION,
    InvokeType.METHOD_NOT_FOUND,
    InvokeType.REMOTE_EXCEPTION
)
annotation class InvokeType {
    companion object {
        /**
         * 执行成功
         */
        const val SUCCESS = 200

        /**
         * 参数错误
         */
        const val BAD_REQUEST = 400

        /**
         * 服务未找到
         */
        const val NOT_FOUND = 404

        /**
         * 服务内部异常
         */
        const val EXCEPTION = 500

        /**
         * 方法未找到
         */
        const val METHOD_NOT_FOUND = 501

        /**
         * 远程方法执行异常
         */
        const val REMOTE_EXCEPTION = 502
    }
}
