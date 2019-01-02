package com.zjy.simplemodule;

import com.zjy.simplemodule.adapter.model.MultipleModel;

public class MainModel extends MultipleModel {

    private String main;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public int getType() {
        return MultipleContracts.TYPE_MAIN;
    }

}
