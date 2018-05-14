package io.hybrid.valhalla.rest.aggregate;

public class ElasticTopHitsClzAggregate extends ElasticTopHitsAggregate {
    private Class clzz;

    public ElasticTopHitsClzAggregate(String name, String field) {
        super(name, field);
    }

    public Class getClzz() {
        return clzz;
    }

    public void setClzz(Class clzz) {
        this.clzz = clzz;
    }
}
