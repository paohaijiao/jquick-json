# jquick json 文档
简体中文 | [英文](./readme-en.md)
```string
jquickjson 是一个轻量高效的 JSON 处理库，采用基于 Java 的哈希映射作为核心数据存储。  
它提供了简单易用的 API，用于解析、生成和操作 JSON 数据。  
```
## 目录
- [简介](#简介)
  - [安装](#安装)
  - [基础用法](#基础用法)
- [功能特性](#功能特性)
  - [1. 对象转JSON字符串](#1-对象转-json-字符串)
  - [2. JSON格式化输出](#2-json-格式化输出)
  - [3. POJO转JSON字符串](#3-pojo-转-json-字符串)
  - [4. 变量合并](#4-变量合并)
  - [5. 字符串转对象](#5-字符串转对象)
- [性能优化](#性能优化)
  - [基准测试](#基准测试)
- [附录](#附录)
## 引入
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
     <artifactId>jquick-json</artifactId>
</dependency>
```
1. 转换成 json 字符串

```java
JUser user = new JUser("John", 30);
JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
String json = serializer.serialize(user);
```
2. 美化输出

```java
        JProduct.ProductDTO.SpecificationsDTO.DimensionsDTO dimensions =
        new JProduct.ProductDTO.SpecificationsDTO.DimensionsDTO();
        dimensions.setLength(120);
        dimensions.setWidth(80);
        dimensions.setHeight(10);
        JProduct.ProductDTO.SpecificationsDTO specifications =
        new JProduct.ProductDTO.SpecificationsDTO();
        specifications.setWeight(2.5);
        specifications.setDimensions(dimensions);
        JProduct.ProductDTO.InventoryDTO inventory =
        new JProduct.ProductDTO.InventoryDTO();
        inventory.setWarehouse1(150);
        inventory.setWarehouse2(75);
        inventory.setTotal(225);
        JProduct.ProductDTO productDTO = new JProduct.ProductDTO();
        productDTO.setId(1001);
        productDTO.setSerialNumber(20230501123456789L);
        productDTO.setIsAvailable(true);
        productDTO.setProductionDate("2023-05-15T08:30:00Z");
        productDTO.setPrice(199.99);
        productDTO.setDiscountRate(0.15);
        productDTO.setSpecifications(specifications);
        productDTO.setTags(Arrays.asList("electronics", "new-arrival", "sale"));
        productDTO.setInventory(inventory);
        JProduct jProduct = new JProduct();
        jProduct.setProduct(productDTO);
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String json = serializer.serialize(jProduct);
        System.out.println(JSONSupport.prettyPrint(json, 3));
```

3. 对象转json字符串

```java
     JProduct1.ProductDTO.SpecificationsDTO.DimensionsDTO dimensions =
                new JProduct1.ProductDTO.SpecificationsDTO.DimensionsDTO();
        dimensions.setLength(120);
        dimensions.setWidth(80);
        dimensions.setHeight(10);
        JProduct1.ProductDTO.SpecificationsDTO specifications =
                new JProduct1.ProductDTO.SpecificationsDTO();
        specifications.setWeight(2.5);
        specifications.setDimensions(dimensions);
        JProduct1.ProductDTO.InventoryDTO inventory =
                new JProduct1.ProductDTO.InventoryDTO();
        inventory.setWarehouse1(150);
        inventory.setWarehouse2(75);
        inventory.setTotal(225);
        JProduct1.ProductDTO productDTO = new JProduct1.ProductDTO();
        productDTO.setId(1001);
        productDTO.setSerialNumber(20230501123456789L);
        productDTO.setIsAvailable(true);
        productDTO.setProductionDate(new Date());
        productDTO.setPrice(199.99);
        productDTO.setDiscountRate(0.15);
        productDTO.setSpecifications(specifications);
        productDTO.setTags(Arrays.asList("electronics", "new-arrival", "sale"));
        productDTO.setInventory(inventory);
        JProduct1 jProduct = new JProduct1();
        jProduct.setProduct(productDTO);
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String json = serializer.serialize(jProduct);
        System.out.println(json);
```
4. 和变量合并
```java
        JUserModel user = new JUserModel("${key}", "${value}");
        JContext context = new JContext();
        context.put("key", "key1");
        context.put("value", "key2");
        JSONSerializer serializer = JSONSerializerFactory.createJQuickSerializer(context);
        String json = serializer.serialize(user);
        System.out.println(json);
```
5. 字符串转对象
```java
        String str = "{\"product\":{\"id\":1001,\"serialNumber\":20230501123456789,\"isAvailable\":true,\"productionDate\":\"2023-05-15T08:30:00Z\",\"price\":199.99,\"discountRate\":0.15,\"specifications\":{\"weight\":2.5,\"dimensions\":{\"length\":120,\"width\":80,\"height\":10}},\"tags\":[\"electronics\",\"new-arrival\",\"sale\"],\"inventory\":{\"warehouse1\":150,\"warehouse2\":75,\"total\":225}}}\n";
        System.out.println(str);
        JContext context = new JContext();
        context.put("key", "key1");
        context.put("value", "key2");
        JSONSerializer serializer = JSONSerializerFactory.createJQuickSerializer(context);
        JProduct json = serializer.deserialize(str, JProduct.class);
        System.out.println(json);
```

# **性能优化**

自 `1.4.0` 起，POJO 与 JSON 之间的互转（`toBean` / `fromBean`）默认基于 [jquick-asm](https://github.com/paohaijiao/jquick-asm) 动态字节码生成，核心优化点：

- **字节码 Bean 访问器**：首次使用某个类型时，用 jquick-asm 在运行时生成一个访问器类（命名空间 `com.github.paohaijiao.mapper.gen.*`），将字段读写编译为直接的 getter/setter 调用（`INVOKEVIRTUAL`），彻底替代逐字段反射，且每个类型只生成一次并缓存。
- **反射兜底**：类型无公共无参构造器、字段缺少 getter/setter、或为接口/抽象类时，自动回退到反射实现（`JReflectionBeanAccessor`），功能与行为不受影响。
- **元信息缓存**：字段名、`@JSONField` 名称/format、`@JSONIgnore` 等元信息每个类型只解析一次，序列化/反序列化期间零重复解析。
- **缺陷修复**：修复 `fromBean` 在字段值为 `null` 时的 NPE、重复的集合/日期类型分派分支。
- **热路径降噪**：移除反序列化热路径上逐次打印解析结果的日志输出（字符串拼接 + 控制台 I/O）。

## 基准测试

项目内置基准测试（[JBeanAccessorBenchmarkTest](src/test/java/com/github/paohaijiao/JBeanAccessorBenchmarkTest.java)），运行方式：

```bash
mvn test -Dtest=JBeanAccessorBenchmarkTest
```

下表为 5 字段 POJO（String / Integer / int / boolean / long）在本机（JDK 1.8.0_191 / Windows 10）测得，仅供参考，建议在目标环境复测：
ns:纳秒/op:一次操作
| 场景 | 反射 ns/op | 字节码 ns/op | 加速比 |
| ---- | ---------- | ------------ | ------ |
| `accessor.get`（单字段读） | 12.2 | 5.3 | 2.3x |
| `accessor.set`（单字段写） | 53.3 | 1.0 | 54.0x |
| `accessor.create`（实例创建） | 2.6 | 1.4 | 1.8x |
| `fromBean`（bean → JSONObject） | 238.8 | 225.8 | 1.1x |
| `toBean`（JSONObject → bean） | 323.4 | 34.5 | 9.4x |
| `serialize` 完整链路 | - | 5,780 | - |
| `deserialize` 完整链路 | - | 19,277 | - |

> 说明：字段写入（`accessor.set`）与 `toBean` 收益最为显著（可达数十倍）；`fromBean` 的主要开销在字段类型分派与 Map 构建，字段读取只占小部分，因此提升相对有限。

# **捐献 ☕**

感谢您使用这个开源项目！它完全免费并将持续维护，但开发者确实需要您的支持。

---

## **如何支持我们**

1. **请我喝杯咖啡**  
   果这个项目为您节省了时间或金钱，请考虑通过小额捐赠支持我。

2. **您的捐赠用途**
- 维持项目运行的服务器成本.
- 开发新功能以提供更多价值.
- 优化文档以提升用户体验.

3. **每一分都很重要**  
   即使是1分钱的捐赠也能激励我熬夜调试！


## **为什么捐赠?**
✔️ 保持项目永远免费且无广告.  
✔️ 支持及时响应问题和社区咨询.  
✔️ 实现计划中的未来功能.

感谢您成为让开源世界更美好的伙伴！

--- 

### **补充说明**
- 本项目和产品维护.
- 您的支持确保其可持续性和成长 .
---

## **🌟 立即支持**
赞助时欢迎通过 [email](mailto:goudingcheng@gmail.com) 留言。您的名字将被列入项目README文件的 **"特别感谢"** 名单中！
![Ali Pay](./src/main/resources/pay/alipay.jpg)
![Wechat Pay](./src/main/resources/pay/wechat.jpg)

---