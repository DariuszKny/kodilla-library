package com.kodillalibrary.domain;




public enum  BookCopyStatus {
    AVAILABLE,
    RENTED;

    @Override
    public String toString() {
        return "BookCopyStatus{}";
    }
}
