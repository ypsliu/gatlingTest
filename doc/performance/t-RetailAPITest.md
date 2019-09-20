# Getting Started

## 1 Introduction

### 1.1 Application

![1563699490511](C:\liuli\project\Oyster\api-gatling-test\doc\performance\performanceImg\NEWIBE_architecture.png)

Application Project:

T-RetailAPI, XDist, LocationAPI,SpringCloudConfigServer,ConfigServer;

Mock Service: WireMock;

DB & MEM: Codis, Redis, Oracle;

MQ: Kafka

### 1.2 Test Target

The target of this test is the performance of t-RetailAPI project. Based on test report, it can help to deploy the product environment. Meanwhile, it gives the guide about how to do t-RetailAPI performance test. 

### 1.3 Tool

The description of tools in this test, like version and so on.

**atop**

Atop is a ASCII full-screen performance monitor which can log and report the activity of all server processes.

**atop-grap**

Graphing **atop** metrics in grafana/influxdb.

Just simple way to create graphics from atop metrics. Now it works for:

- **CPU** information
- **cpu** infirmation per core
- **CPL** CPU load statistic
- **MEM** Memory usage statistics
- **SWP** swapping statistics
- **PAG** paging statistics
- **LVM/MDD/DSK** disk subsystem statistics
- **NET** network statistics per interface and upper level

**NOT** working for process-level statistics:

- "PRG" (general),
- "PRC" (cpu),
- "PRM" (memory),
- "PRD" (disk, only if the kernel-patch has been installed)
- "PRN" (network, only if the kernel-patch has been installed).

**Python**

We use python script to show the service spent time of t-RetailAPI and xDist. 

**ping point**

Pinpoint is a large scale distributed system APM tools with Java.

**gatline**

Gatling is a highly capable load testing tool. It is designed for ease of use, maintainability and high performance.

Out of the box, Gatling comes with excellent support of the HTTP protocol that makes it a tool of choice for load testing any HTTP server.

 

## 2 Installation

### 2.1 Machine

The introduction of these machines in this test.

**Machine**

Guan Jian confirm  

| Column     | description             |
| ---------- | ----------------------- |
| ID         | The id of these machine |
| IP         | The ip of these machine |
| IS_VIRTUAL | True(virtual machine)   |

 

**systemInfo**

https://www.cnblogs.com/cloudos/p/8416415.html

https://www.dwhd.org/20150726_180952.html

 

### 2.2 Deployment

The deployment of every project that appears in this test. 

A deployment graph and some introduction of it.

| Column   | description                               |
| -------- | ----------------------------------------- |
| Machine  | The id of these machine                   |
| IP       | The ip of these machine                   |
| PROJECTS | Projects and tools deploy on this machine |

 

### 2.3 Project Installation

| condition            | description                                                  |
| -------------------- | ------------------------------------------------------------ |
| t-RetailAPI Server   | JDK, Container.The configuration of t-RetailAPI Server and   Installation |
| Location API Server  | JDK, Container.The configuration of Location API Server and   Installation |
| Spring Config Server | JDK, Container.The configuration of Spring Config Server and   Installation |
| Codis Server         | JDK, Container.The configuration of Codis Server and Installation |
| XDist Server         | JDK, Container.The configuration of XDist Server and Installation |
| Oracle Server        | JDK, Container.The configuration of Oracle Server            |
| RabbitMQ Server      | JDK, Container.The configuration of RabbitMQ Server and Installation |

 

### 2.4 Tool Installation

The installation of each tool.

2.4.1 atop

Only atop-2.2.6.tar.gz is surported

 https://www.iots.vip/post/install_atop.html

2.4.2 atop-graph

https://github.com/aplsms/atop-graph

2.4.3 python 

http://gitlab.openjawtech.com/Oyster/api-gatling-test/tree/TDA-53/src/resources/t-RetailAPI

Scripts：

**TRetailAPIAverageTimeMetricsService.py**：average Time in NEWIBE

**TRetailAPITimeGraphMetricsService.py**: time metrics as graph

2.4.4 gatling

The installation of gatling

 

# Test Guids

## 1 Scope

| Condition1  | End point 1  | Description1 |
| ----------- | ------------ | ------------ |
| End point 2 | Description2 |              |
| Condition2  | End point 1  | Description3 |
| End point 3 | Description4 |              |

 

## 2 Project 

The operate of how to start feature project

### 2.1Tomcat

**t-RetailAPI Server** 

**Location API Server** 

**Spring Config Server** 

**XDist Server**

**Codis Server**  

**Oracle Server**

**Kafka**  

## 3 Tool

The operate of how to start test tools.

3.1 atop

3.2 atop-graph

3.3 python 

3.4 catalin

#  Report 

In this test, OJ tests **critical point** and a little higher than it. Red cond means more importance.

### 1 T-RetailAPI Point

#### 1.1 Application Description

| End Point | Rate                   | t-RetailAPI Avg Time(ms) | XDist Avg Time(ms) | Total Avg Time(ms) |
| --------- | ---------------------- | ------------------------ | ------------------ | ------------------ |
| url_1     | Percentage of requests |                          |                    |                    |
| url_2     |                        |                          |                    |                    |

Log collection:

1） copy log : /var/log/atop/atop_20190702

2） Atop:  http://www.361way.com/atop/5162.html

3)    https://github.com/aplsms/atop-graph

#### 1.2 Virtual Machine

The CPU usage section shows the percentage of CPU time spent on various tasks

| CPU usage |                                                              |             |
| --------- | ------------------------------------------------------------ | ----------- |
| Cond      | description                                                  | value       |
| Us        | The us value is the time the CPU spends   executing processes in userspace. | Peak& avage |
| Sy        | The sy value is the time spent on running   kernelspace processes. | Peak& avage |
| Id        | The CPU remains idle.                                        | Peak& avage |
| Wa        | The time the CPU spends waiting for I/O to complete.         | Peak& avage |

 

Table1

| CPU  |      |      |      |      |
| ---- | ---- | ---- | ---- | ---- |
| Cond | Us   | Sy   | Id   | Wa   |
|      |      |      |      |      |

 

The “memory” section shows information regarding the memory usage of the system.

| Memory usage |                                          |             |
| ------------ | ---------------------------------------- | ----------- |
| Cond         | description                              | value       |
| Mem          | RAM space about total, free and used.    | Peak& avage |
| Swap         | swap space about total, free and used.   | Peak& avage |
| Tomcat       | tomcat space about total, free and used. | Peak& avage |

 

Table2

| Memory |      |      |        |
| ------ | ---- | ---- | ------ |
| Cond   | Mem  | Swap | Tomcat |
|        |      |      |        |

 

The “IO” section shows IOPS and throughput.

| IO usage   |                                                              |       |
| ---------- | ------------------------------------------------------------ | ----- |
| Cond       | description                                                  | value |
| IOPS       | Input/Output Operations Per Second, a common   performance measurement used to benchmark computer storage devices like hard   disk drives (HDD), solid state drives (SSD).( https://www.cnblogs.com/94cool/p/5662256.html) | avage |
| Throughput | measures the number of bits read or written   per second.    | avage |
| logSize    | Log size per day. 1min                                       | avage |

 

Table3

| IO   |      |            |         |
| ---- | ---- | ---------- | ------- |
| Cond | IOPS | Throughput | LogSize |
|      |      |            |         |

 

The “NIO” section shows the performance of Network IO.

| NIO usage       |                                                              |             |
| --------------- | ------------------------------------------------------------ | ----------- |
| Cond            | description                                                  | value       |
| Network inflow  | The value of Network inflow. Bit                             | Peak        |
| Network outflow | The value of Network outflow. Bit                            | Peak        |
| connection      | Total number of network connections and types   of it. 文件描述符 | Peak& avage |

 

Table4

| NIO  |        |         |            |
| ---- | ------ | ------- | ---------- |
| Cond | Inflow | Outflow | Connection |
|      |        |         |            |

 

The “Load” is a measure of the amount of computational work a system performs. It gives you a relative measure of how long you must wait for things to get done.

| Load usage   |                                                         |       |
| ------------ | ------------------------------------------------------- | ----- |
| Cond         | description                                             | value |
| Load average | The average “load” over one, five and fifteen   minutes | Avage |
| Load peak    | The peak    “load”                                      | Peak  |

 

Table5

| Load |       |      |
| ---- | ----- | ---- |
| Cond | Avage | Peak |
|      |       |      |

Table6（Optional）

|      | CPU  | Memory | IO   | NIO  | Load |      |      |      |      |        |      |            |         |        |         |            |       |      |
| ---- | ---- | ------ | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ------ | ---- | ---------- | ------- | ------ | ------- | ---------- | ----- | ---- |
| Cond | Us   | Sy     | Id   | Wa   | Ni   | Hi   | Si   | Mem  | Swap | tomcat | IOPS | throughput | logSize | inflow | outflow | connection | avage | peak |
|      |      |        |      |      |      |      |      |      |      |        |      |            |         |        |         |            |       |      |

 

#### 1.3 JVM

##### 1.3.1 Gc(From gc log)

This section, we need give a gc report, may be a graph. It also shows what kind of GC strategy we use. 

https://gceasy.io/

https://blog.csdn.net/chuchus/article/details/53435828

##### 1.3.2 Heap

This section, we show the performance of JVM Heap usage and Non-Heap usage.

| Heap usage |                                                              |
| ---------- | ------------------------------------------------------------ |
| Cond       | description                                                  |
| Heap       | It shows the performance of Heap space, like   max and used. |
| Non-Heap   | It shows the performance of Non-Heap space,   like max and used. |

 

##### 1.3.3 Activity thread

This section, we need give a report about the information of activity thread, like the peak and average.

##### 1.3.4 Connection

The tomcat or JBoss connection.

#### 1.2 Graph (produce by py2)

#### 1.3 Summary

 

## 2 Issues

 

 

 

 

