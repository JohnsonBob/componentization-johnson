package com.johnson.compiler.bean

import javax.lang.model.type.TypeMirror

/**
 * @Author: Johnson
 * @CreateDate: 2021/9/24 10:22
 * @Description:
 */
data class Parameter(var type: TypeMirror, var nullable: Boolean = true)