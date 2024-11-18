# 记录 git 使用问题 OR 知识点

# git alias
> 别名,有助于快速输入 git 命令: 
git config --global alias.st status
git config --global alias.pl pull
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

    `git worktree add ../new-dir (可选项:拉取指定分支)`

- IDE 打开新项目 new-dir
- 切换为正常分支(和已开项目分支不同)
  
  `git worktree list` 查看所有 worktree 名称

  `git worktree remove -f 名称` -f 代表强制删除,可以删除包含 submodule 的工程
  
---

# git set

> 设置邮箱为例：
`git config --global user.email "MY_NAME@example.com"`


# git cherry-pick

> Git 中的一个非常有用的命令，它允许你选择并应用一个或多个已经存在的提交，而不必合并整个分支或复制一系列提交。这在许多情况下都很有用，例如，当你只想从一个分支中选取某个特定的提交，并将其应用到另一个分支时。


1. 应用一个特定的提交

  如果你想从一个分支（例如 feature-branch）中选取一个特定的提交（例如 commit-hash），并将其应用到当前分支，你可以这样做：

 `git cherry-pick commit-hash`

 1. 应用多个提交

 你可以通过指定多个提交哈希值来应用多个提交：

 `git cherry-pick commit-hash1 commit-hash2`

 3. 应用一个范围的提交
 你还可以应用一个范围内的所有提交。例如，要应用从 commit-hash1 到 commit-hash2（包括这两个提交）之间的所有提交，你可以这样做：

 `git cherry-pick commit-hash1..commit-hash2`

 4. 冲突

 如果在 `cherry-pick` 过程中遇到冲突，Git 会暂停并让你解决冲突。解决冲突后，你需要运行 `git cherry-pick --continue` 来继续 `cherry-pick` 过程。如果你决定放弃 `cherry-pick`，可以使用 `git cherry-pick --abort` 来撤销