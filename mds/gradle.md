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
















### 上传到maven库

#### 一、上传到jcenter
    
    见lib/uploadjcenter.gradle（要在build.gradle中apply from）
    
    
    
 1.  在[bintray](https://bintray.com/)注册账号(或者用github,google登录)

 2.  新建仓库（repository)（product不用建） 如libs,type选择maven  
 
     ![新建仓库](./pic/gradle/pic1.png)
     
     ![新建仓库](./pic/gradle/pic3.png)
 
 3.  点击刚刚新建的仓库，再点击adb new package添加package（ version control 建议用github ）
     
     ![package](./pic/gradle/pic2.png)
     
     ![package](./pic/gradle/pic4.png)
  
 4.  在项目根build.gradle添加
 
     classpath 'com.github.dcendents:android-maven-gradle-plugin:1.5'
 
     classpath "com.jfrog.bintray.gradle:gradle-bintray-plugin:1.5"
  
 5.  在lib下面新建uploadjcenter.gradle（具体配置见代码）
     需要在lib下新建local.properies并加入两行
     *  bintray.user=xxxxxxxx  (bintray的用户名)
     *  bintray.apikey=xxxxxxxxxx（bintray--edit profie----apikey）
     *  在build.gradle增加一行 apply from: 'uploadjcenter.gradle'
     
 6.  在as----terminal下运行
      ./gradlew install 
      如成功  接着运行
      ./gradlew bintrayUpload
      （不行的话  需要先chmod 755 gradlew）
     
 7.  上传成功后在后台可以看到上传的项目
      
     
     ![package](./pic/gradle/pic5.png)
     
 8.  点击页面右下方的 add to jcenter   发布到jcenter(大概一天过审核，审核后才能像其他类库一样可以依赖)
    
     add to jcenter的时候，下面的框不用勾
     
     ![package](./pic/gradle/pic6.png)
     
     
#### 二、上传到本地maven仓库

   1.  本地maven仓库nexus搭建见doc/nexus
   2.  上传代码见lib/uploadnative.gradle
   3.   在lib/gradle.properties添加（不存在则创建）
        GROUP_ID=com.fastcome1985.lib
        ARTIFACT_ID=luo
        VERSION=0.0.5-SNAPSHOT
        RELEASE_REPOSITORY_URL=http://172.23.132.67:8081/repository/ljxrelease/
        SNAPSHOT_REPOSITORY_URL=http://172.23.132.67:8081/repository/ljxsnapshot3/
        USERNAME=ttt
        PASSWORD=ttt
        
        注：VERSION=0.0.5-SNAPSHOT，带-SNAPSHOT会传到SNAPSHOT_REPOSITORY_URL，不带则是release
     
   4. 双击右边的upload/uploadArchives  就可以上传了

#### 三、遗留问题
   1.  上传到jcenter 与本地仓库不能一起用，会编译不过

### 多渠道打包    
    
   1. 第一步 在AndroidManifest.xml里配置PlaceHolder
    
    `  <meta-data
         android:name="UMENG_CHANNEL"
         android:value="${UMENG_CHANNEL_VALUE}" />`
         
   2. 在build.gradle设置productFlavors
   
   `productFlavors {
            xiaomi {}
            _360 {}
            baidu {}
            wandoujia {}
        }  
    productFlavors.all { 
            flavor -> flavor.manifestPlaceholders = [UMENG_CHANNEL_VALUE: name] 
        }
   `
   
   3. 直接执行 ./gradlew assembleRelease



    
     
     
     
     
     
     