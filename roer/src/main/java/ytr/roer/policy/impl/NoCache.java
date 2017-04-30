package ytr.roer.policy.impl;

import ytr.roer.policy.CachePolicy;

/**
 * Created by tianrui on 17-4-29.
 */
public class NoCache implements CachePolicy {
    @Override
    public Entry get(String cacheKey) {
        return null;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void put(String cacheKey, Entry cacheEntry) {

    }
}
