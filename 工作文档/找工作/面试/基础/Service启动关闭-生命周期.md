# 介绍一下 Service 的（started）绑定的（bound）， 不同区别， 是否可以多次调用， 多次调用后如何关闭服务

在 Android 中，Service 是用于在后台执行长时间运行任务的组件。它主要有两种启动模式：启动服务（Started Service） 和 绑定服务（Bound Service）。

 ##  启动服务（Started Service）
启动服务是在后台运行不与任何特定的组件绑定的任务。它通常是由某个组件（如 Activity 或 BroadcastReceiver）调用 startService() 方法启动的。服务一旦启动，就会在后台运行，即使启动它的组件已经被销毁。

特点：

一旦启动，服务将一直运行，直到自己调用 stopSelf() 方法，或其他组件调用 stopService() 方法来显式停止它。
启动的服务可以被多次调用。每次调用 startService() 后，服务的 onStartCommand() 方法会被再次执行，但不会重新创建服务实例（除非服务已经停止）。

关闭服务：

可以通过调用 stopSelf() 或外部调用 stopService() 方法来关闭启动的服务。
多次调用 startService() 后，stopService() 只要调用一次就会触发 onDestory() 。


## 绑定服务（Bound Service）
绑定服务允许其他应用组件（如 Activity）通过调用 bindService() 方法绑定到服务，并与之进行交互（例如通过接口调用服务中的方法）。一旦组件与服务绑定，服务就可以为该组件提供客户端-服务端的交互功能。

特点：

当最后一个绑定的组件解除绑定时，服务将自动停止（除非同时它是启动服务）。
绑定服务通常用于需要与前台组件持续通信的任务，例如通过 IPC（进程间通信）与其他组件交互。
绑定服务可以支持多个组件同时绑定，每个绑定的组件都可以调用服务中的方法。

关闭服务：

当所有绑定的组件都解除绑定时，服务会自动停止。你可以通过调用 unbindService() 方法解除绑定。
