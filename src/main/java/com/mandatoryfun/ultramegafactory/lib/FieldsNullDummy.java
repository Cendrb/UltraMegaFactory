package com.mandatoryfun.ultramegafactory.lib;

/**
 * Created by cendr_000 on 21.05.2016.
 */
public class FieldsNullDummy implements IFieldsSuck {
    @Override
    public int getField(int id) {
        // always return zero
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        // we don't have any fields
    }

    @Override
    public int getFieldCount() {
        return Integer.MAX_VALUE;
    }
}
