package com.thoughtworks.ketsu.domain;

import java.util.Map;

public interface DomainBuilder<T> {
    T build(Map<String, Object> info);
}
