SQLDataSet
===================================
English | [中文](README-CN.md)

A simple orm, deserialize data from `JDBC ResultSet` as java bean.

For Android SQLite also see [SQLiteDataSet](https://github.com/Yeamy/SQLiteDataSet)

```groovy
    implementation 'io.github.yeamy:sqldataset:1.1'
```
### 1. Annotation
``````
public class Fruit {

    @DsColumn("Name")
    public String name;      // the column in database is "Name"

    public String fullName;  // the column in database can be "fullName" or "full_name"

    @DsIgnore
    public String count;     // ignore this field

    public FruitType type;   // the name of the column is the same as the field
    // regist as custom type (see DsAdapter)

    public Skin skin;        // this field no DsColumn treat as extra type
}
```

### 2. DsReader
Generally, using `DsReader` is an easy and fast way.

```
Statement stmt = ...;                                 // the source
String sql = "SELECT ...";                            // the sql
Fruit apple = DsReader.read(stmt, sql, Fruit.class);  // read one
ArrayList<Fruit> list = DsReader.eadArray(stmt, sql, Fruit.class);
```

### 3. DsFactory\<T> & DsAdapter
In order to deserialize custom type, you may define a `DsFactory`.

```java
DsFactory<Fruit> factory = new DsFactory<>() {
    //...
};

Fruit fruit = DsReader.read(stmt, sql, factory);
```

### 4. DsObserver
If you want to do anything after the Bean read, you can implement `DsObserver.class`, and do it in `onDsFinish()`.

```
public class Vegetables implements DsObserver {

    @DsColumn("Name")
    public String name;
    ...
    @Override
    public void onDsFinish(){}
}

```

### 5. Extra Field
Data come from same row of ResultSet can deserialize into an extra field.

source table:

|UserName|Province|CityName|...|
|:-:|:-:|:-:|:-:|
|Nike|Guangdong|Shantou|...|
|...|

Usually, deserialize like this:

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

to package `province` and `city` into same field `location`, see below:

```
public class User {

    @DsColumn("UserName")
    public String name;
    ...

    // NOTICE：must not declare annotation with DsColumn nor DsIgnore.
    public City location;
}

public class City {

    @DsColumn("Province")
    public String province;

    @DsColumn("CityName")
    public String city;
    ...
}

```
