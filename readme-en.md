# jquick json Document
```string
    jquickjson is a lightweight and efficient json processing library that utilizes
a java-based hashmap for core data storage. it provides a simple and user-friendly api for parsing, 
generating, and manipulating json data.
```
## Table of Contents
- [Introduction](#introduction)
    - [Installation](#installation)
    - [Basic Usage](#basic-usage)
- [Features](#features)
    - [1. Convert Object to JSON String](#1-convert-object-to-json-string)
    - [2. Pretty Print JSON](#2-pretty-print-json)
    - [3. POJO to JSON String](#3-pojo-to-json-string)
    - [4. Merge with Variables](#4-merge-with-variables)
    - [5. String to Object Conversion](#5-string-to-object-conversion)
- [Performance](#performance)
    - [Benchmark](#benchmark)
- [Appendix](#appendix)
## Introduction
```xml
<dependency>
    <groupId>io.github.paohaijiao</groupId>
     <artifactId>jquick-json</artifactId>
</dependency>
```
1. to json String

```java
JUser user = new JUser("John", 30);
JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
String json = serializer.serialize(user);
```
2. prettyPrint

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

3. pojo toJsonString

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
4. merge with variable
```java
        JUserModel user = new JUserModel("${key}", "${value}");
        JContext context = new JContext();
        context.put("key", "key1");
        context.put("value", "key2");
        JSONSerializer serializer = JSONSerializerFactory.createJQuickSerializer(context);
        String json = serializer.serialize(user);
        System.out.println(json);
```
5. string to object
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

# **Performance**

Since `1.4.0`, the conversion between POJOs and JSON (`toBean` / `fromBean`) is built on dynamic bytecode generation with [jquick-asm](https://github.com/paohaijiao/jquick-asm). Key optimizations:

- **Bytecode Bean Accessor**: on first use of a type, jquick-asm generates an accessor class at runtime (namespace `com.github.paohaijiao.mapper.gen.*`), compiling field reads/writes into direct getter/setter calls (`INVOKEVIRTUAL`) instead of per-field reflection. Each type is generated only once and cached.
- **Reflection Fallback**: types without a public no-arg constructor, fields without getters/setters, interfaces, or abstract classes automatically fall back to the reflection implementation (`JReflectionBeanAccessor`) with identical behavior.
- **Metadata Caching**: field names, `@JSONField` name/format, and `@JSONIgnore` metadata are resolved once per type — zero repeated resolution during serialization/deserialization.
- **Bug Fixes**: fixed the NPE in `fromBean` when a field value is `null`, and removed duplicate collection/date dispatch branches.
- **Hot-path Noise Removal**: removed the per-parse log that prints the full parse result on the deserialization hot path (string concatenation + console I/O).

## Benchmark

A built-in benchmark ([JBeanAccessorBenchmarkTest](src/test/java/com/github/paohaijiao/JBeanAccessorBenchmarkTest.java)) is provided:

```bash
mvn test -Dtest=JBeanAccessorBenchmarkTest
```

Results below were measured with a 5-field POJO (String / Integer / int / boolean / long) on JDK 1.8.0_191 / Windows 10 — reference only, re-run on your target environment:

| Scenario | Reflection ns/op | Bytecode ns/op | Speedup |
| -------- | ---------------- | -------------- | ------- |
| `accessor.get` (single field read) | 12.2 | 5.3 | 2.3x |
| `accessor.set` (single field write) | 53.3 | 1.0 | 54.0x |
| `accessor.create` (instance creation) | 2.6 | 1.4 | 1.8x |
| `fromBean` (bean → JSONObject) | 238.8 | 225.8 | 1.1x |
| `toBean` (JSONObject → bean) | 323.4 | 34.5 | 9.4x |
| `serialize` full pipeline | - | 5,780 | - |
| `deserialize` full pipeline | - | 19,277 | - |

> Field writes (`accessor.set`) and `toBean` benefit the most (up to tens of times faster); `fromBean` is dominated by field-type dispatch and map building, with field reads accounting for only a small fraction, so its gain is modest.

# **Generating Power with Love (and Caffeine) ☕**

Thank you for using this open-source project! It is completely free and will be maintained continuously, but the developers do need your support.

---

## **How You Can Help**

1. **Buy Me a Coffee**  
   If this project has saved you time or money, please consider supporting me with a small donation.

2. **Where Your Donation Goes**
- Server costs to keep the project running.
- Feature development to add more value.
- Documentation optimization for a better user experience.

3. **Every Cent Counts**  
   Even a donation of just 1 cent motivates me to debug late into the night!



## **Why Donate?**
✔️ Keep the project **free and ad-free** forever.  
✔️ Support timely responses to issues and community inquiries.  
✔️ Enable planned features for the future.

Thank you for being a partner in making the open-source world better!

--- 

### **Additional Notes**
- The project is maintained with love and caffeine.
- Your support ensures its sustainability and growth.
---

## **🌟 Support Now**
Feel free to leave a message via [email](mailto:goudingcheng@gmail.com) when sponsoring. Your name will be included in the **"Special Thanks"** list in the project's README file!
![OCBC Pay Now](./src/main/resources/pay/paynow.jpg)
![Touch n Go ](./src/main/resources/pay/tngGo.jpg)
---