# Banner

基于ViewPager2实现的轮播控件，目前实现层叠卡片效果，后续会加入更多的效果。

## 运行效果
![Oops! The screenshot is missing!](https://github.com/fulvmei/Banner/raw/master/screenshots/overlay.png)

## 使用步骤
### 1. 在project的build.gradle添加如下代码
```
allprojects {
     repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

### 2. 在Module的build.gradle添加依赖

```
dependencies {
  ...
  implementation 'com.github.fulvmei:Banner:1.0.0'
}
```

### 3. 在布局文件里添加控件
``` xml
<com.chengfu.android.banner.Banner
        android:id="@+id/banner"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

### 4. Banner参数设置
``` java
    //...
    Banner banner=findViewById(R.id.banner);
    banner.setAdapter(new Adapter());
    banner.setOffscreenPageLimit(4);
    banner.setPageTransformer(new OverlayTransformer(4,0.03f,24));
    banner.setOffsetRight(3*24);
```
