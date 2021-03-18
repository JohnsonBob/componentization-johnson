#### 1、AutoService与gradle版本不兼容
> 1.1 注解处理器需要添加的依赖:
```
    implementation 'com.google.auto.service:auto-service:1.0-rc7' //自动注册注解处理器
    kapt 'com.google.auto.service:auto-service:1.0-rc7' //自动注册注解处理器
```
> 1.2 gradle 版本
```
distributionUrl=https\://services.gradle.org/distributions/gradle-5.6.4-all.zip
classpath "com.android.tools.build:gradle:3.5.3"
```