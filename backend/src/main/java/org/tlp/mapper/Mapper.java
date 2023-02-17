package org.tlp.mapper;

public interface Mapper<A, B>{
    B mapTo(A obj);
    A mapFrom(B obj);
}
