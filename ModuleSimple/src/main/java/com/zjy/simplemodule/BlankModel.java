package com.zjy.simplemodule;

import com.zjy.simplemodule.adapter.model.MultipleModel;

public class BlankModel extends MultipleModel {

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int getType() {
        return MultipleContracts.TYPE_BLANK;
    }
}
