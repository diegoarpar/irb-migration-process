package com.irb.migration.service.transforms;

import java.util.List;

public interface ETLTransformation<T, P> {

    public List<T> TransformData(List<P> origin );
}
