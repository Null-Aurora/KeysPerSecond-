# <img src="KeysPerSecond/resources/kps.png" width="40"/> KeysPerSecond [![](https://img.shields.io/github/release/RoanH/KeysPerSecond.svg)](https://github.com/RoanH/KeysPerSecond/releases) [![](https://img.shields.io/github/downloads/RoanH/KeysPerSecond/total.svg)](#downloads)

KeysPerSecond 是一个统计特定按键和鼠标按钮按下次数的程序。它还可以显示各种统计数据，如每秒按键的平均值、最大值和当前值。该程序还可以显示按键次数随时间变化的图表。几乎所有方面都可以完全自定义。

[跳转到下载](#downloads)

# 简介
最初我想知道在 osu! 中每秒按了多少次键，并且已经在直播中见过类似的程序。
然而，我找不到一个在我的电脑上运行良好的程序，所以我决定自己编写一个。

程序运行时的界面如下（最右侧的图表显示光标移动）：    
![界面](https://media.roanh.dev/keyspersecond/kps1.png)  ![预览](https://media.roanh.dev/keyspersecond/preview.gif)  ![预览](https://media.roanh.dev/keyspersecond/cursorgraph.gif)    
还有一个右键菜单可以配置所有设置：    
![菜单](https://media.roanh.dev/keyspersecond/rmenu88.png)

对于每个配置的按键，程序将显示其被按下的次数。默认情况下，它还会显示每秒按键的最大值、平均值和当前值。
启用后，它还可以显示每秒按键次数随时间变化的图表、按键总数以及自上次输入以来的时间。还提供显示光标移动的图表，更多图表类型和面板类型正在计划中。

上图中显示的所有内容都可以打开或关闭，所有面板都可以自由重新排列。      
![配置](https://media.roanh.dev/keyspersecond/cmain88.png)

还有一些可以发送给程序的命令，但是有些默认未绑定，如果您已经频繁使用这些组合键，可能需要重新绑定其他命令。默认启用的命令有：
- **Ctrl + U**：终止程序。
- **Ctrl + Y**：显示 GUI 或隐藏到系统托盘。
- **Ctrl + T**：暂停或恢复输入跟踪和面板更新。

您还可以使用箭头键移动程序或将其贴靠到屏幕边缘。

我希望你们中的一些人觉得这个程序有用，并且/或者会在您的直播中使用它（我很乐意看到这种情况发生 :) ）。
如果您发现任何错误，请随时报告。如果您有任何想要添加的功能，也请告诉我！

## 注意事项
- 图表中的水平线表示每秒按键的平均次数。
- 光标图表的平滑度很大程度上取决于更新速率。
- 如果您想查看以管理员权限运行的应用程序（例如，原神）的输入，那么 KeysPerSecond 也应该以管理员权限执行，请参阅 [问题 #104](https://github.com/RoanH/KeysPerSecond/issues/104)。
- 您可以配置一个在启动时自动打开的配置，更多详情请参阅 [此 wiki 页面](https://github.com/RoanH/KeysPerSecond/wiki)。
- 您可以在程序中添加任意数量的按键或鼠标按钮。
- 要在颜色菜单中更改 GUI 颜色，请单击当前颜色。
- 您可以使用箭头键以 3 种不同的速度移动窗口，每次移动 1、2 和 3 个像素（2=Ctrl，3=Shift）。
- 重置某些内容时，如果此程序使用 cmd/shell 运行，它也会打印到控制台。
- 置顶选项远非完美，它只是要求操作系统将程序置于顶部。它不会覆盖大多数全屏游戏。[对于某些人](https://youtu.be/E_WHAaI_-Zw)来说，在 Windows 98 / ME 兼容模式下运行程序可以使其覆盖全屏 osu!，所以如果幸运的话，这可能会奏效。
- 如果您有旧的配置文件（kpsconf1 或 kpsconf2），则需要使用 [版本 8.4](https://github.com/RoanH/KeysPerSecond/releases/tag/v8.4) 进行转换，以便在更新版本中加载。
- 窗口模式可以在不支持无边框窗口的操作系统（例如 Chromebook）上提供帮助。请注意，窗口模式不支持透明窗口。

## 下载
_需要 Java 8 或更高版本_    
_测试过的操作系统：Mac 10.11.6 & M1, Ubuntu Linux 16.04 LTS, Windows 7 & 8 & 10 & 11_
- [Windows 可执行文件](https://github.com/RoanH/KeysPerSecond/releases/download/v8.9/KeysPerSecond-v8.9.exe)
- [可运行 Java 归档文件](https://github.com/RoanH/KeysPerSecond/releases/download/v8.9/KeysPerSecond-v8.9.jar)

所有版本：[发布版本](https://github.com/RoanH/KeysPerSecond/releases)    
GitHub 仓库：[这里](https://github.com/RoanH/KeysPerSecond)    
原始 osu! 论坛帖子：[帖子](https://osu.ppy.sh/community/forums/topics/552405)

## 示例
以下两个示例显示了编辑时的布局。所有面板都必须与网格对齐，但网格单元的大小可以更改。    
![](https://media.roanh.dev/keyspersecond/ex1.png)    
![](https://media.roanh.dev/keyspersecond/ex2.png)    
接下来是另外两个可能的布局示例。    
![](https://media.roanh.dev/keyspersecond/ex3.png)    
![](https://media.roanh.dev/keyspersecond/ex4.png)    
最后是一些非常简单的布局，以突出显示更多标题-值显示选项。    
![模式](https://media.roanh.dev/keyspersecond/lmodes.png)

## 历史
项目开发开始于：2017年1月23日。
