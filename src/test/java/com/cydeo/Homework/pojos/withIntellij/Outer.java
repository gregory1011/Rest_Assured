
package com.cydeo.Homework.pojos.withIntellij;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Outer {

    @JsonProperty("MRData")
    private MRData mRData;

    public MRData getMRData() {
        return mRData;
    }

    public void setMRData(MRData mRData) {
        this.mRData = mRData;
    }

}
