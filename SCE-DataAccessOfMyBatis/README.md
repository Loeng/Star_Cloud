# SCE-DataAccess

本项目为星云教育平台项目中的数据交互组件，用以对需要进行持久化的数据进行 CRUD 操作。

## 架构设计

![原本的架构设计](../README-IMG/data-access-original-design.png)

如上图所示，数据交互服务用于操作 NOSQL 中以非结构化数据。但考虑到项目工期问题，现阶段暂时将其简化为：

![简化设计](D:\Developer\projects\bonc\StarCloud\code\main\README-IMG\data-access-simplify.png)