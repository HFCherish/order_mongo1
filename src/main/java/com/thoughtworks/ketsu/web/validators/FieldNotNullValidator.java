package com.thoughtworks.ketsu.web.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldNotNullValidator {

    public Map<String, List> getNullFields(List<String> toValidates, Map<String, Object> info) {
        List<Map<String, String>> nullFields = new ArrayList<>();

        for(String toValidate: toValidates) {
            if( info.get(toValidate) == null )
                nullFields.add(new HashMap<String, String>(){{
                    put("field", toValidate);
                    put("message", toValidate + " can not be empty.");
                }});
        }
        return new HashMap<String, List>() {{
            put("items", nullFields);
        }};
    }

}
