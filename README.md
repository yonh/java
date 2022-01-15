# 自定义持久层框架设计思路：

### 1. 自定义持久层框架
1. 加载配置文件加载  
   加载配置文件成字节输入流，存储在内存中，创建 `Resources` 类
   
2. 创建2个javaBean
   1. `Configuration`: 核心配置类，存放`sqlMapConfig.xml`解析对象
   2. `MappedStatement`: 映射配置类，存放mapper.xml解析对象
    
3. 解析配置文件  
   创建类：SqlSessionFactoryBuilder, build(inputSteam in)
   1. 使用 `dom4j` 解析配置文件，将解析出来的内容封装到容器对象  
   2. 创建 `SqlSessionFactory` 对象,生产 `sqlSession`（会话对象)

4. 创建 `SqlSessionFactory` 接口及其实现类, `DefaultSqlSessionFactory`  
   
5. 创建 `SqlSession` 接口及其实现类, `DefaultSqlSession`  
   定义数据库curd操作：`selectOne()`, `selectList()`, `update()`, `delete()`
6. 创建 `Executor` 接口及其实现类  `SimpleExecutor`
   实现 `query(Configuration, MappedStatement, Object... params)`（就是执行jdbc代码）

### 2. 使用端
提供两部分配置
1. **数据库配置信息**   
   sqlMapConfig.xml: 存放数据库配置信息，存放mapper.xml路径
2. **sql配置信息**  
   mapper.xml: 存放sql配置信息，sql语句，参数类型，返回值类型  
   sql的唯一标识(statementId)：`namespace.id`
   
   
