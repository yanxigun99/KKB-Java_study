**ES\****从入门到精通**

**一、\****ES***\*安装**

**1\****、下载***\*ES**

下载：（文件比较大，建议手动下载）

下载网址：

**2\****、安装**

**解压：**

tar -zxvf elasticsearch-6.2.4.tar.gz

注意：把elasticsearch软件必须放入/home/es（es是新建用户）的目录下，并把elasticsearch设置为es用户所属

**创建日志、数据存储目录：（留作备用，初次先创建）**

mkdir -p /data/logs/es

mkdir -p /data/es/{data,work,plugins,scripts}

**创建用户**

useradd es -s /bin/bash #es**不能在**root**用户下启动，必须创建新的用户，用来启动**es

**启动：\****./elasticsearch**

注意：es不能在root用户下启动，必须创建新的用户，用来启动es

**切换用户：** **su es**

再次启动，发现还是报错，原因：当前用户没有执行权限

**授权：\****chown -R es:es elasticsearch-6.2.4**

授权成功，发现elasticsearch已经在es用户下面了，可以启动了，但是启动成功，浏览器不能访问，因此还需要做如下配置：

配置修改：**

再次启动：报如下错误

1）max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]

每个进程最大同时打开文件数太小，可通过下面2个命令查看当前数量

ulimit -Hn

ulimit -Sn

修改/etc/security/limits.conf文件，增加配置，用户退出后重新登录生效

*

soft

nofile

65536

*

hard

nofile

65536

2）max number of threads [3818] for user [es] is too low, increase to at least [4096] 可通过命令查看

ulimit -Hu

ulimit -Su

问题同上，最大线程个数太低。修改配置文件/etc/security/limits.conf，增加配置

`*

soft

nproc

4096`

`*

hard

nproc

4096`

3）、max virtual memory areas vm.max_map_count [65530] is too low, increase to at least

[262144]

修改/etc/sysctl.conf文件

vi /etc/sysctl.conf

sysctl -p #**执行命令**sysctl -p**生效**

\#**增加配置**vm.max_map_count=262144

错误解决完毕：重新启动

后台启动：

./elasticsearch -d

**3\****、容器安装**

\#**搜索镜像**

docker search elasticsearch

\#**拉取镜像**

docker pull elasticsearch:6.2.4

\#**创建容器**

docker create --name elasticsearch --net host -e "discovery.type=single-node" -e "network.host=192.168.66.66" elasticsearch:6.2.4

\#**启动**

docker start elasticsearch

\#**查看日志**

docker logs elasticsearch

访问容器elasticsearch:

后台

**二、\****head***\*插件安装**

**1\****、***\*head\****插件主要用途**

elasticsearch-head是一个用来浏览、与Elastic Search簇进行交互的web前端展示插件。

elasticsearch-head是一个用来监控Elastic Search状态的客户端插件。

elasticsearch主要有以下三个主要操作—— 1）簇浏览，显示簇的拓扑并允许你执行索引（index)和节点层面的操作。 2）查询接口，允许你查询簇并以原始json格式或表格的形式显示检索结果。 3）显示簇状态，有许多快速访问的tabs用来显示簇的状态。 4）支持Restful API接口，包含了许多选项产生感兴趣的结果，包括： 第一，请求方式:get,put,post,delete; json请求数据，节点node， 路径path。 第二，JSON验证器。 第三，定时请求的能力。 第四，用javascript表达式传输结果的能力。 第五，统计一段时间的结果或该段时间结果比对的能力。

第六，以简单图标的形式绘制传输结果

**2\****、安装**

安装步骤：

\#**下载**nodejs,head**插件运行依赖**node

wget https://nodejs.org/dist/v9.9.0/node-v9.9.0-linux-x64.tar.xz

\#**解压**

tar -xf node-v9.9.0-linux-x64.tar.xz

\#**重命名**

mv node-v9.9.0-linux-x64 nodeJs

\#**配置文件**

vim /etc/profile

\#**刷新配置**

source /etc/profile

\#**查询**node**版本，同时查看是否安装成功**

node -v

\#**下载**head**插件**

wget https://github.com/mobz/elasticsearch-head/archive/master.zip

\#**解压**

unzip master.zip

\#**使用淘宝的镜像库进行下载，速度很快**

npm install -g cnpm --registry=[https://registry.npm.taobao.org](https://registry.npm.taobao.org/)

\#**进入**head**插件解压目录，执行安装命令**

cnpm install

**3\****、运行**

npm start #**启动**head**插件**

启动运行端口为：9100

访问：

此时未连接，需要配置才能连接：

**修改** **Gruntfile.js\****文件：**

修改如下：

**修改\****_site/app.js**

修改IP地址，连接elasticsearch

**启用\****CORS**:

当head插件访问es时，您必须在elasticsearch中启用CORS，否则您的浏览器将拒绝跨域。

在elasticsearch配置中：

http.cors.enabled: true

您还必须设置，http.cors.allow-origin因为默认情况下不允许跨域。http.cors.allow-origin: "*" 是允许配置的，但由于这样配置的任何地方都可以访问，所以有安全风险。 我在集群安装的时候已经配好了、如果你刚配置、需要重启ElasticSearch服务

http.cors.enabled: true

http.cors.allow-origin: "*"

访问head**插件**