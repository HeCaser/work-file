
在进行大前端开发时, 会用到各种 packages. 有些 package 要求使用特定版本的 node, nvm 是进行 node 版本切换的工具

[nvm 官网地址](https://github.com/nvm-sh/nvm) : 管理 node 版本

查看 node 版本 `node -v`


# 安装以及解决 nvm 命令找不到问题
https://stackoverflow.com/questions/16904658/node-version-manager-install-nvm-command-not-found

-  安装 curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.1/install.sh | bash

- 全局生效 Important... - DO NOT forget to Restart your terminal OR use command source ~/.nvm/nvm.sh (this will refresh the available commands in your system path).

- 查看 nvm 版本 `nvm --version` 输出 `0.*.*` 代表成功


# nvm 使用

1. 安装 node `nvm install 16`
2. 切换版本 `nvm use 16`
3. 查看 node 使用切换成功 `node --version`

