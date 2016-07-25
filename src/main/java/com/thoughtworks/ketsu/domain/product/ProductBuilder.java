//package com.thoughtworks.ketsu.domain.product;
//
//import com.thoughtworks.ketsu.domain.DomainBuilder;
//
//import java.util.Map;
//
//public class ProductBuilder implements DomainBuilder<Product> {
//    @Override
//    public Product build(Map<String, Object> info) {
//        return new Product(info.get("name").toString(),
//                info.get("description").toString(),
//                (double) info.get("price"));
//    }
//}
