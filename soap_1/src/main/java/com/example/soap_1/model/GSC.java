package com.example.soap_1.model;

public class GSC {

    private String attribute_name;

    private String attribute_value;

    private String attribute_desc;

    public GSC() {
    }

    public GSC(String attribute_name, String attribute_value, String attribute_desc) {
        this.attribute_name = attribute_name;
        this.attribute_value = attribute_value;
        this.attribute_desc = attribute_desc;
    }

    public String getAttributeName() {
        return attribute_name;
    }

    public void setAttributeName(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public String getAttributeValue() {
        return attribute_value;
    }

    public void setAttributeValue(String attribute_value) {
        this.attribute_value = attribute_value;
    }

    public String getAttributeDesc() {
        return attribute_desc;
    }

    public void setAttributeDesc(String attribute_desc) {
        this.attribute_desc = attribute_desc;
    }
}
