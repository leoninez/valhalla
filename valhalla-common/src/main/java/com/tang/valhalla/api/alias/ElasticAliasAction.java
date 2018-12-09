package com.tang.valhalla.api.alias;


public class ElasticAliasAction {
    public String index;
    public String alias;
    public Action action;

    public ElasticAliasAction(String index, String alias, Action action) {
        this.index = index;
        this.alias = alias;
        this.action = action;
    }

    public static enum Action {
        ADD,
        REMOVE
    }
}
