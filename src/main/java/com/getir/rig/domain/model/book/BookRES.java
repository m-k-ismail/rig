package com.getir.rig.domain.model.book;

public class BookRES {
    private Book data;

    public BookRES(Book data) {
        this.data = data;
    }

    public Book getData() {
        return data;
    }

    public void setData(Book data) {
        this.data = data;
    }

}
