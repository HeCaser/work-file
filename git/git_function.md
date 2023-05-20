# 记录 git 使用问题 OR 知识点

# git alias
> 别名,有助于快速输入 git 命令: git config --global alias.st status
```
// 常用配置
alias.co=checkout
alias.br=branch
alias.ci=commit
alias.st=status
alias.sm=submodule
alias.cp=cherry-pick
```
---

# git worktree
> 实现同一项目打开多个 git 窗口, 提高开发效率

步骤
-  创建 workfile, 指定一个不存在的分支

    `git worktree add ../new-dir not-existing_branch`

- IDE 打开新项目 new-dir
- 切换为正常分支(和已开项目分支不同)
  
---




