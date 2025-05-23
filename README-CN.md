SQLDataSet
===================================
[English](README.md) | 中文

这个项目是一个简单的数据库读取工具，将ResultSet反序列生成对象集。

Android平台SQLite可以看这里[SQLiteDataSet](https://github.com/Yeamy/SQLiteDataSet)

```groovy
    implementation 'io.github.yeamy:sqldataset:2.0'
```
### 1. Bean类声明
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // 声明此参数对应列名为Name

    public String fullName;  // 列名可以是 "fullName" 或者 "full_name"

    @DsIgnore
    public String count;     // 声明此参数不读取
    
    public FruitType type;   // 不添加此声明，取参数名做列名，此参数为自定义类型（见下文 DsAdapter）

    public Skin skin;        // 没有声明DsColumn的参数当做扩展参数处理
}

```

### 2. DsReader
一般情况使用DsReader工具类快速读取已足矣

```java
Statement stmt = ...;                                 // 数据源
String sql = "SELECT ...";                            // 筛选的SQL语句
Fruit apple = DsReader.read(stmt, sql, Fruit.class);
ArrayList<Fruit> list = DsReader.readArray(stmt, sql, Fruit.class);
```

### 3. DsFieldFactory\<T>
使用自定义工厂类解析列。

```java
DsReader.register(MyField.class, new DsFieldReader<MyField>() {

    @Override
    public MyField read(ResultSet rs, int columnIndex) throws SQLException, ReflectiveOperationException {
        return new MyType(rs.getString(columnIndex));
    }
);
DsReader.read(stmt, String sql, MyType.class);
// or
DsReader.with(MyField.class, new DsFieldReader<MyField>() {

    @Override
    public MyField read(ResultSet rs, int columnIndex) throws SQLException, ReflectiveOperationException {
        return new MyType(rs.getString(columnIndex));
    }

).read(stmt, String sql, MyType.class);
```


### 4. DsObserver
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

### 5. 扩展对象
来自ResultSet的同一行数据可以被解析到同一实例内。

数据表:

|UserName|Province|CityName|...|
|:-:|:-:|:-:|:-:|
|Nike|Guangdong|Shantou|...|
|...|

通常会采用如下数据类：

```
public class User {
    @DsColumn("UserName")
    public String name;

    @DsColumn("Province")
    public String province;

    @DsColumn("CityName")
    public String city;
    ...
}

```

为了将province和city封装到同个参数内，可以使用如下方式：

```java
public class User {
    @DsColumn("UserName")
    public String name;
    ...
    // 注意：参数不能声明DsColumn，参数名不能与列名重复，
    // 否则只能使用DsAdapter来解析
    public City location;
}

public class City {
    @DsColumn("Province")
    public String province;

    @DsColumn("CityName")
    public String city;
    ...
}
