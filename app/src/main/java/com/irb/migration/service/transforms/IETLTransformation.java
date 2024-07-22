package com.irb.migration.service.transforms;

import java.util.List;
import java.util.Map;

public interface IETLTransformation<T, P> {

    public List<T> TransformData(List<P> origin );
    public List<T> TransformData(List<P> origin, Map... data );
}
