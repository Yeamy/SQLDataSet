SQL Data Set
===================================
[English](README.md) | 中文

这个项目是一个简单的数据库读取工具，将ResultSet反序列生成对象集。

### 1. Bean类声明
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // 声明此参数对应列名为Name
    
    @DsIgnore
    public String count;     // 声明此参数不读取
    
    public FruitType type;   // 不添加此声明，对应列名与参数名相同

    @DsExtra
    public Skin skin;   // 声明此参数为扩展参数
}

```

### 2. DsReader
一般情况使用DsReader工具类快速读取

```java
Statement stmt = ...;                                 // 数据源
String sql = "SELECT ...";                            // 筛选的SQL语句
Fruit apple = DsReader.read(stmt, sql, Fruit.class);
ArrayList<Fruit> list = r DsReader.eadArray(stmt, sql, Fruit.class);
```

### 3. DsFactory\<T>
使用工厂类生产对象，工厂类

```java
java.sql.ResultSet rs = ...;                           // 数据来源
DsFactory<Fruit> factory = new DsFactory(Fruit.class); // 实例化工厂

Fruit apple = factory.read(rs);                        // 读取单个

factory.readArray(list, rs);                           // 读取多个

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, rs);                           // 自定义list
```

### 4. DsAdapter\<T>
工厂类默认支持基本类型、URL、时间、String，所有对应的参数见`com.yeamy.sql.ds.DsType`，其他类型的对象可以使用DsAdapter来创建。

```java
DsAdapter<Fruit> adapter = new DsAdapter() {

    /**
     * @param t
     *           基础类型的成员变量已读取，可以直接使用
     * @param field
     *           对应需要读取的参数，使用field.getName()区分
     * @param rs
     *           数据库搜索结果
     * @param columnIndex
     *           对应参数在rs中对应的位置
     */
    @Override
    public void read(Fruit t, Field field, ResultSet rs, int columnIndex) {
        t.type = new FruitType(....);
    }
};
factory.addAdapter(Type.class, adapter);
```

### 5. DsObserver
如果导入DsObserver接口，解析结束后会调用onDsFinish()方法，可以在此方法修改数据。

```java
public class Vegetables implements DsObserver {
    @DsColumn("Name")
    public String name;
    ...
    @Override
    public void onDsFinish(){}
}

```

### 6. DsExtra
该声明由DsAdapter实现。来自ResultSet的同一行数据可以被解析到同一实例内。

数据表:

|UserName|Province|CityName|...|
|:-:|:-:|:-:|:-:|
|Nike|Guangdong|Shantou|...|
|...|

数据类：

```java
public class User {
    @DsColumn("UserName")
    public String name;
    ...
    @DsExtra
    public City city;
}

public class City {
    @DsColumn("Province")
    public String province;

    @DsColumn("CityName")
    public String name;
    ...
}

```
