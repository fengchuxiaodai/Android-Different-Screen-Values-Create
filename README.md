# Android-Different-Screen-Values-Create

 目前是有两种基准分辨率，标准机型测试都没问题，奇葩的（1800x1080-240dpi/1920x1080-440dpi等等）测试也ok，目前是生成的多个values，都是sw适配最小宽度dp
 
 
# 目前有个idea
  
  因为有多个values，造成打包不方便（每次碰到奇葩分辨率都要手动加进去，然后生成values，然后添加进android工程，太麻烦），所以有个不太成熟的不知道怎么做的想法，就是能否在values留一份dimens，然后动态生成当前app所在设备的对应的values，进行加载；目前是想在application中生成……
  
  
# 梦想总是要有的，
#     万一实现了呢？！
