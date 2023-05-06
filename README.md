# 第一步
新建Android工程
# 第二步
##### 打开`AndroidManifest.xml`，在`activity`下新建`intent-filter`标签
##### 在`intent-filter`标签底下增加

```
<action android:name="android.intent.action.VIEW" />
<category android:name="android.intent.category.DEFAULT" />
<category android:name="android.intent.category.BROWSABLE" />
<category android:name="android.intent.category.APP_BROWSER" />
<data
    android:host="test"
    android:path="/product"
    android:port="8000"
    android:scheme="openapp"
 />
```

##### 第一行和第三行是必加配置，可以从其他APP跳转，第四行是用来实现从浏览器跳转的配置
##### data内的`android:host（可选）`表从外部启动的主机号
##### data内的`android:path（可选）`表从外部启动的传参路径
##### data内的`android:port（可选）`表从外部启动的端口号 `除了80端口之外，其他任意数字皆可`
##### data内的`android:host（必填）`表从外部启动的协议号（可自定义）
# 第三步
##### 在主Activity的启动函数内增加以下代码，其中第四行用来获取从外部传入的参数。解析方式类似于http参数的解析

```
Intent intent = getIntent();
String action = intent.getAction();
if (Intent.ACTION_VIEW.equals(action)) {
    Uri uri = intent.getData();
}
```
# 第四步
##### 从其他APP调用方式，填入被调用APP中配置的URL+需要传入的参数

```
Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("openapp://test:8000/product?param1=Test1&param2=param2"));
        startActivity(it);
```
# 其他说明
##### 所用AndroidStudio版本为 2021.3  
##### JDK和SDK均为自动下载