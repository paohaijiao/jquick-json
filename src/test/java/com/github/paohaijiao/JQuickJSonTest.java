package com.github.paohaijiao;

import com.github.paohaijiao.factory.JSONSerializerFactory;
import com.github.paohaijiao.model.*;
import com.github.paohaijiao.serializer.JSONSerializer;
import com.github.paohaijiao.param.JContext;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class JQuickJSonTest {

    @Test
    public void test() throws IOException {
        JUser user = new JUser("John", 30);
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String json = serializer.serialize(user);
        System.out.println(json);
    }
    @Test
    public void test1() throws IOException {
        JMember user = new JMember("John", 30);
        JSONSerializer serializer = JSONSerializerFactory.getDefaultSerializer();
        String json = serializer.serialize(user);
        System.out.println(json);
    }
    @Test
    public void test2() throws IOException {
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
        System.out.println(json);
    }
    @Test
    public void test3() throws IOException {
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
    }
    @Test
    public void test11() throws IOException {
        JUserModel user = new JUserModel("${key}", "${value}");
        JContext context=new JContext();
        context.put("key","key1");
        context.put("value","key2");
        JSONSerializer serializer = JSONSerializerFactory.createJQuickSerializer(context);
        String json = serializer.serialize(user);
        System.out.println(json);
    }
    @Test
    public void test12() throws IOException {
        String str="{\"product\":{\"id\":1001,\"serialNumber\":20230501123456789,\"isAvailable\":true,\"productionDate\":\"2023-05-15T08:30:00Z\",\"price\":199.99,\"discountRate\":0.15,\"specifications\":{\"weight\":2.5,\"dimensions\":{\"length\":120,\"width\":80,\"height\":10}},\"tags\":[\"electronics\",\"new-arrival\",\"sale\"],\"inventory\":{\"warehouse1\":150,\"warehouse2\":75,\"total\":225}}}\n";
        System.out.println(str);
        JContext context=new JContext();
        context.put("key","key1");
        context.put("value","key2");
        JSONSerializer serializer = JSONSerializerFactory.createJQuickSerializer(context);
        JProduct json = serializer.deserialize(str,JProduct.class);
        System.out.println(json);
    }

}
