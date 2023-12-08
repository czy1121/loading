# loading

一些 loading 动画

- 一些 `animated-vector` 实现的SVG动画，参考 https://github.com/n3r4zzurr0/svg-spinners
- `DoubleColorBallLoadingView` - 从抖音扒下来的双色球加载动画
- `SimpleLoadingView` - 播放加载动画
  - 如果 `drawable` 是 `Animatable`，直接播放
  - 否则对此视图播放 `RotateAnimation`，插值器效果类似于 SVG 的 `calcMode="discrete"`   
    `Interpolator { (it * 12).toInt() / 12f }`


**`calcMode="discrete"`**

这指定了动画函数将从一个值跳转到下一个值，而不需要任何插值。

https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/calcMode 

安卓的 `animated-vector` 不支持 `discrete`，也难以实现类似的效果

## Preview

![](https://raw.githubusercontent.com/n3r4zzurr0/svg-spinners/main/preview/3-dots-bounce-black-36.svg) 
![](https://raw.githubusercontent.com/n3r4zzurr0/svg-spinners/main/preview/3-dots-fade-black-36.svg) 

 
![](https://raw.githubusercontent.com/n3r4zzurr0/svg-spinners/main/preview/3-dots-move-black-36.svg) 
![](https://raw.githubusercontent.com/n3r4zzurr0/svg-spinners/main/preview/3-dots-rotate-black-36.svg) 



![](https://raw.githubusercontent.com/n3r4zzurr0/svg-spinners/main/preview/3-dots-scale-black-36.svg) 
![](https://raw.githubusercontent.com/n3r4zzurr0/svg-spinners/main/preview/3-dots-scale-middle-black-36.svg) 




## Gradle

``` groovy
repositories {
    maven { url "https://gitee.com/ezy/repo/raw/cosmo/"}
}
dependencies { 
    implementation "me.reezy.cosmo:loading:0.8.0" 
}
```

## 参考

https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html
https://developer.android.com/reference/android/animation/ObjectAnimator
https://developer.android.com/guide/topics/resources/animation-resource

https://www.nan.fyi/svg-paths/
https://developer.mozilla.org/zh-CN/docs/Web/SVG/Tutorial/Paths

https://github.com/n3r4zzurr0/svg-spinners

SMIL Animation   
https://www.w3.org/TR/2001/REC-smil-animation-20010904/


## LICENSE

The Component is open-sourced software licensed under the [Apache license](LICENSE).