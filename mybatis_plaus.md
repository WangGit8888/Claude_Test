生成样板代码规范: 基础mybatis-plaus文件

1、根据表名生成上层文件下,在此文件夹下进行以下操作
2、业务代码都都要写在对应的实现类里,不要在controller里直接写代码
3、在我发你的基础目录下生成标准文件夹,dto,vo有必要时再生成
    controller
    dao
    model
    service
        serviceImpl
    dto
    vo
4、在上述文件夹下分别生成mybatis-plus代码
5、业务代码写在实现层serviceImpl里不要在controller里写业务代码
6、实体类使用lombok的@Data注解
    