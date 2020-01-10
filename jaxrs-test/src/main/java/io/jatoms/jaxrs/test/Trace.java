package io.jatoms.jaxrs.test;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Trace {

    private String type;

    private int error;

    private Map<String, String> meta;

    private Object metrics;

    private long duration;

    private long parent_id;

    private long start;

    private String resource;

    private String name;

    private String service;

    private long trace_id;

    private long span_id;

}