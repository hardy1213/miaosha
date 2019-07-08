### 一、mysql异常之: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or represents more than one time zone

`mysql> show variables like '%time_zone%';
 +------------------+--------+
 | Variable_name    | Value  |
 +------------------+--------+
 | system_time_zone |        |
 | time_zone        | SYSTEM |
 +------------------+--------+
 2 rows in set, 1 warning (0.23 sec)
 
 mysql> set time_zone='+8:00';
 Query OK, 0 rows affected (0.00 sec)`
 
 ### 二、Metronic
 ### 三、keyProperty="id" useGeneratedKeys="true"
 获取主键ID
 ### 四、Hibernate Validator Engine
 基本校验
 <!-- https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator -->
 <dependency>
     <groupId>org.hibernate.validator</groupId>
     <artifactId>hibernate-validator</artifactId>
     <version>6.0.13.Final</version>
 </dependency>


 