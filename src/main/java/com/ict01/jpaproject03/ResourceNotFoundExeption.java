package com.ict01.jpaproject03;


// ResourceNotFoundExeption 오류를 해결하기 위한 class
public class ResourceNotFoundExeption extends Exception {

    public static final long serialVersionUID = 1L;

    public ResourceNotFoundExeption(Object resourceId){
        super(resourceId != null ? resourceId.toString() : null);
    }
}
