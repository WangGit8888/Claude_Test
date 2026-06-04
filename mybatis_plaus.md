生成样板代码规范: mybatis-plaus文件

1、根据表名生成上层文件下,在此文件夹下进行以下操作
2、业务代码都都要写在对应的实现类里,不要在controller里直接写代码
3、在我发你的基础目录下生成标准文件夹,dto,vo有必要时再生成
    【代码结构按照下面的来】
    controller
    mapper
    entity
    service
        serviceImpl
4、在上述文件夹下分别生成mybatis-plus代码
5、**业务代码写在实现层serviceImpl里**不要在controller里写业务代码
6、实体类使用lombok的@Data注解
7、service层里生成 
    1、查:分页查询,查询的入参固定为 com.example.common.dto.RequestDTO;这里要写一个buildLambdaQueryWrapper方法来构造查询条件，初始情况可以什么条件都不写等待后续命令再生成，后面的如果要生成导出也需要调此方法来实现按条件导出
       查询条件需要时写在com.example.common.dto.Condition里,其中字段String keyword; 用作模糊查询固定字段
    2、增/改 这里写成一个方法用mybatis-plaus的saveOrUpdate来实现
    3、删:写成批量删除的
