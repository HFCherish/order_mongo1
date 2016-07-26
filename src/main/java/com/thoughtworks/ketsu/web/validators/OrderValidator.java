package com.thoughtworks.ketsu.web.validators;

import com.thoughtworks.ketsu.domain.product.ProductRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import java.util.*;

public class OrderValidator extends FieldNotNullValidator {
    @Inject
    ProductRepository productRepository;

    public Map<String, List> getNullFields(Map<String, Object> info) {
        Map<String, List> basicMap = super.getNullFields(Arrays.asList("name", "address", "phone", "order_items"), info);
        List<Map<String, String>> nullFields = getErrorItems(basicMap);

        List<Map> order_items = (List) info.get("order_items");
        if (order_items != null) {
            for (Map item : order_items) {
                Map<String, List> nullItemFields = super.getNullFields(Arrays.asList("product_id", "quantity"), item);
                nullFields.addAll(getErrorItems(nullItemFields));

                Object product_id = item.get("product_id");
                if (product_id != null && !productRepository.findById(new ObjectId(product_id.toString())).isPresent()) {
                    nullFields.add(new HashMap() {
                        {
                            put("field", "product_id");
                            put("message", "product_id is invalid");
                        }
                    });
                }
            }
        }

        if (nullFields.size() == 0) return null;
        return new HashMap<String, List>() {{
            put("items", nullFields);
        }};
    }

    private List<Map<String, String>> getErrorItems(Map<String, List> errorMap) {
        return errorMap == null ? new ArrayList<>() : errorMap.get("items");
    }

}
