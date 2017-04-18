# Gradle使用
   书籍资料见  doc/gradle
   
   
## 内容   

### tasks
  Gradle编译以task为执行对象。

1. 在编译器右侧Gradle选项卡，可以看到各种tasks。

2. 一些常用tasks 
   * assemble 针对每个版本创建一个apk
   
   * clean 删除所有的构建任务，包含apk文件
   
   * check 执行Lint检查并且能够在Lint检测到错误后停止执行脚本
   
   * build 执行assemble和check






### 上传到maven库的task
    *   见lib/upload.gradle（要在在build.gradle中apply from）
   
    *  