package com.example.BlogAPi.Exception;

import lombok.Data;


public class CustomException extends RuntimeException{
    private  String fieldName;
    private  String filedResource ;
    private  Long httpStatus;

    public CustomException() {
    }

    public CustomException(String fieldName, String filedResource, Long httpStatus) {
        super(String.format("Something wrong in %s %s %d" ,fieldName + ":", filedResource, httpStatus));
        this.fieldName = fieldName;
        this.filedResource = filedResource;
        this.httpStatus = httpStatus;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFiledResource() {
        return filedResource;
    }

    public void setFiledResource(String filedResource) {
        this.filedResource = filedResource;
    }

    public Long getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Long httpStatus) {
        this.httpStatus = httpStatus;
    }
}
