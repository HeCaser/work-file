本文记录在 Mac 电脑上配置全局信息, 其中包含了 jdk 自动切换的实现方式

对于 zsh 平台, 执行 `vim .zshrc` 可以查看其内容

工作中常见配置如下

```
export ANDROID_HOME='/Users/hepan/Library/Android/sdk'
export PATH=${PATH}:${ANDROID_HOME}/tools
export PATH=${PATH}:${ANDROID_HOME}/tools/bin
export PATH=${PATH}:${ANDROID_HOME}/emulator
export PATH=${PATH}:${ANDROID_HOME}/platform-tools
export PATH=${PATH}:/usr/libexec/
export PATH=${PATH}:/usr/local/bin
export HOMEBREW_NO_AUTO_UPDATE=1

export JAVA_8_HOME="$(/usr/libexec/java_home -v 1.8)"
export JAVA_11_HOME="$(/usr/libexec/java_home -v 11)"
alias jdk8='export JAVA_HOME=$JAVA_8_HOME'
alias jdk11='export JAVA_HOME=$JAVA_11_HOME'
export JAVA_HOME=$JAVA_8_HOME$
~                                       
```

上面的 `/usr/libexec/java_home -v 1.8` 可以获取 jdk 1.8 全路径 (前提是已安装),同理可以获取 `jdk 11` 的路径. 然后利用别名命令 `alias` 来改变 `JAVA_HOME` 的指向.

在控制台输入 `jdk8` 则可以在当前目录下切换 jdk 版本.

