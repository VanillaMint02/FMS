package com.fms.validators;

public enum AcceptedFileTypes {
    TXT("txt"),
    PDF("pdf"),
    JPG("jpg"),
    JPEG("jpeg");

    public final String label;

    AcceptedFileTypes(String label) {
        this.label = label;
    }


}
