查看 Flutter 配置 `flutter config --list`

去掉某个平台 `flutter config --no-enable-macos-desktop`

开启某个平台 `flutter config --enable-android`

看下 config 命令 `flutter config --help` 可以查看具体有哪些平台可以配置

设置 flutter 的 JDK `flutter config --jdk-dir=<JDK_DIRECTORY>`

拉取包 `flutter pub get   flutter pub upgrade`


## 本地 Flutter 

通过设置 PATH 来设置全局 Flutter 版本

export PATH="/Users/panhe/flutter/flutter-3.24.5/bin:$PATH"

通过  `echo $PATH` 查看配置是否成功。
通过 `flutter --version` 查看生效版本

在 sereal-flutter 项目中会有多个 PATH。 不知道啥原因。