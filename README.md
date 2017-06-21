SQL Data Set
===================================

这个项目是一个简单的数据库读取工具，将ResultSet反序列生产对象集。

### 1. 声明
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // 声明此参数对应列名为Count
    
    @DsIgnore
    public String count;     // 声明此参数不读取
    
    public FruitType type;   // 不添加此声明，对应列名与参数名相同
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

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, rs);                           // 读取多个并保存到list
```

### 4. DsAdapter\<T>
工厂类默认支持基本类型、URL、时间、String，所有对应的参数见`com.yeamy.sql.ds.DsType`，其他类型的对象可以使用DsAdapter来创建。

```java
DsAdapter<Fruit> adapter = new DsAdapter() {
    @Override
    public void read(Fruit t, Field field, ResultSet rs, int columnIndex) {
        // t 的基础类型已存在，可以使用
        // field 为对应需要读取的参数，注意区分多个相同类的对象
        // rs 为数据来源
        // columnIndex 对应参数在rs中对应的位置
        // 注意：结果需要赋值给t
        t.type = new FruitType(....);
    }
};
factory.addAdapter(FruitType.class, adapter);
```
