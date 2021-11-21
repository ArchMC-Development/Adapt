package com.volmit.adapt.util;

@SuppressWarnings("hiding")
@FunctionalInterface
public interface Consumer3<A, B, C> {
    void accept(A a, B b, C c);
}
