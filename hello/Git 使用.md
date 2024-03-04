Git 使用

```
仓库（Repository）： 一个Git仓库是一个存储你项目所有文件和历史版本的地方。
提交（Commit）： 提交是保存代码更改的操作，每次提交会生成一个唯一的哈希值用于标识。
分支（Branch）： 分支是用来进行并行开发的，你可以在分支上进行实验性的更改而不影响主线。
主分支（Main Branch）： 通常是main或master分支，代表项目的稳定版本。
合并（Merge）： 将一个分支的更改合并到另一个分支。
冲突（Conflict）： 当多个分支的更改发生冲突时，需要手动解决。




```

1.用户信息配置

```java
3.1、配置用户信息(必要)
配置用户信息是非常重要的，因为每次你提交代码时都会有相应的提交者信息。

git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

3.2、配置文本编辑器(非必要)
你可以配置一个默认的文本编辑器，以便你在进行提交信息或解决冲突时编辑文本。例如，如果你想使用Visual Studio Code：

git config --global core.editor "code --wait"

3.3、配置别名(非必要，新手不建议)
Git支持设置别名，使得你可以更快速地输入命令。比如，你可以设置一个别名"co"来代替"checkout"命令：

git config --global alias.co checkout

3.4、查看配置
你可以使用以下命令来查看当前的Git配置：

git config --list
这会列出所有的全局和本地配置设置。

3.5、配置忽略文件
有些文件你可能希望Git自动忽略，例如编译产生的临时文件或敏感数据文件。你可以创建一个名为.gitignore的文件，并将需要忽略的文件和模式添加到其中。

# 示例 .gitignore 文件
*.log
node_modules/
secret.txt

通过这些配置，你可以将Git按照你的喜好进行个性化设置，从而更好地适应你的工作流程。
```

2.关联远程仓库

```
关联远程仓库
将本地仓库与远程仓库关联起来，以便你可以推送和拉取代码。
注意：本地Git仓库和远程仓库之间的传输是通过SSH加密的，所以还需要将你的SSH密钥添加到远程仓库设置中。

git remote add origin <remote-repository-url>
在这里，<remote-repository-url>是远程仓库的URL。

7.2、推送代码到远程仓库
一旦关联了远程仓库，你可以将本地代码推送到远程仓库。

git push origin <branch-name>
这会将指定分支的代码推送到远程仓库。首次推送时，你可能需要使用 -u 参数来建立跟踪关系。

7.3、拉取代码
从远程仓库拉取代码更新，以保持你的本地代码同步。

git pull origin <branch-name>
这会将指定分支的最新代码拉取到你的本地仓库。

7.4、解决远程仓库冲突
在多人协作时，可能会遇到远程仓库的冲突。在推送代码之前，确保从远程仓库拉取最新的代码，以避免冲突。

7.5、查看远程仓库
你可以使用以下命令查看与本地仓库关联的远程仓库。

git remote -v
通过以上远程仓库操作，你可以实现代码的共享和协作，确保团队成员之间的代码同步。请根据你的实际项目需求和使用情况对上述内容进行编辑和扩展。

8、高级操作
除了基本操作外，Git还提供了一些高级操作，可以帮助你更灵活地管理代码和历史记录。在本节中，你将学习如何重写历史、使用储藏功能以及管理子模块。

8.1、重写历史
在某些情况下，你可能需要修改之前的提交历史，如合并、重新排序或删除提交。

修改最近一次提交信息：git commit --amend
交互式地修改提交历史：git rebase -i
8.2、使用储藏和恢复
储藏功能允许你将当前未提交的更改暂存起来，以便在切换分支或执行其他操作时恢复使用。

储藏当前更改：git stash
恢复储藏的更改：git stash apply 或 git stash pop
8.3、管理子模块
子模块允许你在一个Git仓库中嵌套另一个仓库，这对于依赖的管理非常有用。

添加子模块：git submodule add
更新子模块：git submodule update --init --recursive
删除子模块：详见Git文档
通过高级操作，你可以更灵活地处理提交历史、更改管理以及子模块等复杂情况。请根据你的实际项目需求和使用情况对上述内容进行编辑和扩展。


```

3.初始化仓库

```
1.初始化仓库 git init
2.查看工作目录和暂存区的当前状态 git status
3.添加文件到暂存区
	//添加指定文件到暂存区
git add <filename>
//添加所有文件到暂存区
git add .
4.提交更改到版本库 本地
git commit -m "Commit message"
5.查看提交历史
查看项目的提交历史，了解每次提交的详细信息。

//查看提交历史
git log

//以精简形式显示提交历史
git log --oneline
6.比较工作目录和暂存区之间的差异
比较工作目录和暂存区之间的差异，或者比较两个提交之间的差异。

//比较工作目录和暂存区之间的差异
git diff

//比较暂存区和最新提交之间的差异
git diff --staged
//或
git diff --cached

//比较两个提交之间的差异
git diff <commit-hash-1> <commit-hash-2>

//比较指定文件的差异
git diff <filename>
7.此时想要进行版本回退有三种方式

//回退到上个版本
git reset --hard HEAD^

//回退到上上个版本
git reset --hard HEAD^^

//回退到前10个版本
git reset --hard HEAD~10

//回退到指定版本号的版本
git reset --hard a7f365c128827cdf1f0d796141ce3c644cc78770


如果此时我们还想要重新回到版本回退前的版本，则可以使用git reset --hard [版本号] 来实现

//查询版本号
git reflog

//回退版本
git reset --hard [版本号]

8.恢复工作目录中的更改
如果在修改添加到暂存区之前，想要撤销某个文件的修改可以使用以下命令

//将指定文件恢复到版本库中的版本
git restore <filename>

//将所有文件恢复到版本库中的版本
git restore .

//注意，旧版本不支持restore命令，需要使用该命令
git checkout -- <filename>

9.撤销提交
当你需要撤销之前的提交时，你可以使用git revert命令来创建一个新的提交，将之前的提交内容进行反转。这允许你保留之前的提交历史，同时也纠正了之前的更改。

//撤销一个提交
git revert [版本号]

//可以指定一个范围，撤销多个连续的提交
git revert <start-commit>..<end-commit>

10.创建分支
创建一个新分支，用于开发新功能或修复问题，保持主线代码不受影响。

//创建一个分支
git branch <branch-name>

//创建并切换到分支
git branch -b <branch-name>

11.切换分支
切换到另一个分支，以便在不同的分支上进行不同的工作。

//切换分支
git checkout <branch-name>

//查看当前所在分支
git checkout
12.
将一个分支的更改合并到另一个分支中。

git merge <branch-name>

13.git branch -d <branch-name> 删除无用分支
```





在某些情况下，你可能需要修改之前的提交历史，如合并、重新排序或删除提交。

- 修改最近一次提交信息：git commit --amend
- 交互式地修改提交历史：git rebase -i

#### 8.2、使用储藏和恢复

储藏功能允许你将当前未提交的更改暂存起来，以便在切换分支或执行其他操作时恢复使用。

- 储藏当前更改：git stash
- 恢复储藏的更改：git stash apply 或 git stash pop

#### 8.3、管理子模块

子模块允许你在一个Git仓库中嵌套另一个仓库，这对于依赖的管理非常有用。

- 添加子模块：git submodule add
- 更新子模块：git submodule update --init --recursive
- 删除子模块：详见Git文档

通过高级操作，你可以更灵活地处理提交历史、更改管理以及子模块等复杂情况。请根据你的实际项目需求和使用情况对上述内容进行编辑和扩展。









#### 常用 Git 命令速查表

##### 基本操作

- 初始化仓库：git init
- 克隆远程仓库：git clone <repository-url>
- 查看状态：git status
- 添加文件到暂存区：git add <filename>
- 提交更改：git commit -m “Commit message”
- 查看提交历史：git log
- 查看文件差异：git diff <filename>
- 查看远程仓库列表：git remote -v
- 查看远程分支列表：git branch -r

##### 分支操作

- 创建分支：git branch <branch-name>
- 切换分支：git checkout <branch-name> 或 git switch <branch-name>
- 创建并切换分支：git checkout -b <branch-name> 或 git switch -c <branch-name>
- 合并分支：git merge <branch-name>
- 删除分支：git branch -d <branch-name>

##### 远程仓库操作

- 关联远程仓库：git remote add <remote-name> <repository-url>
- 推送代码：git push <remote-name> <branch-name>
- 拉取代码：git pull <remote-name> <branch-name>
- 创建Pull Request：在远程仓库上操作
- 获取远程仓库更新：git fetch

##### 高级操作

- 重写历史：git commit --amend、git rebase -i <commit-hash>
- 使用储藏：git stash、git stash apply、git stash pop
- 管理子模块：git submodule add <repository-url>
- 重置和回退：git reset --soft <commit-hash>、git reset --hard <commit-hash>、git reflog

##### 团队协作

- 创建Pull Request：在远程仓库上操作
- 代码审查：通过Pull Request 进行
- 解决冲突：手动编辑文件解决冲突
- 分支保护和权限管理：在远程仓库上设置

##### 常见问题解决

- 解决冲突：手动编辑冲突文件
- 撤销错误提交：git reset --soft HEAD^、git reset --hard HEAD^、重写历史
- 丢弃未提交更改：git restore <filename>
- 追踪和忽略文件：git rm --cached <filename>、创建 .gitignore 文件