# 网关中同步技巧学习
## 1. websocket
### 1.1 什么是websocket
    websocket是基于TCP的一个应用层协议，是一种全双工通信的协议，即不仅客户端能够发送信息给服务端，服务端也能够主动发送信息给客户端。
websocket通过ping-pong心跳机制维持连接，tcp通过发送keep-alive信息也能维持连接的活跃，但是如果在keep-alive时间范围内客户端没有发送信息
则该连接也关闭。总的来说，websocket相比http的优势有两点：1. 服务端能够主动发送信息给客户端；2. 连接能够维持避免重新建立连接的网络的开销。

### 1.2 websocket原理

![websocket建立通信心跳]("")
## 2. http长连接