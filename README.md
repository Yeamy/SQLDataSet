SQL Data Set
===================================
English | [中文](README-CN.md)

This project is a simple tools to unSerialize Java Bean from JDBC ResultSet.

### 1. Annotation
```java
public class Fruit {

    @DsColumn("Name")
    public String name;      // the column in database is "Name"
    
    @DsIgnore
    public String count;     // ignore this field
    
    public FruitType type;   // the name of the column is the same as the field
}
```

### 2. DsReader
Generally, using DsReader is a easy and fast way.

```java
Statement stmt = ...;                                 // the source
String sql = "SELECT ...";                            // the sql
Fruit apple = DsReader.read(stmt, sql, Fruit.class);  // read one
ArrayList<Fruit> list = r DsReader.eadArray(stmt, sql, Fruit.class);
```

### 3. DsFactory\<T>
Advanced, using The DsFactory to solve custom field reading.

```java
java.sql.ResultSet rs = ...;                           // the data source
DsFactory<Fruit> factory = new DsFactory(Fruit.class); // build a factory

Fruit apple = factory.read(rs);                        // read one

factory.readArray(rs);                                 // read array 1

List<Fruit> list = new ArrayList<Fruit>();
factory.readArray(list, rs);                           // read array 2
```

### 4. DsAdapter\<T>
The DsFactory support base type in sql, such as int, long, String, URL, time. 

Using `DsAdapter` to unserialize custom field.

```java
DsAdapter<Fruit> adapter = new DsAdapter() {

    /**
     * @param t
     *           any other base type field has been unserialized,
     * @param field
     *           using field.getName() to distinguish same type.
     * @param rs
     *           jdbc select result,
     * @param columnIndex
     *           the index of the target column in ResultSet.
     */
    @Override
    public void read(Fruit t, Field field, ResultSet rs, int columnIndex) {
        t.type = new FruitType(....);
    }
};
factory.addAdapter(Type.class, adapter);
```

### 5. DsObserver
If you want to do anything when the Bean has bean readed, you can implements DsObserver.class, and do it in onDsFinish().

```java
public class Vegetables implements DsObserver {
    @DsColumn("Name")
    public String name;
    ...
    @Override
    public void onDsFinish(){}
}

```
