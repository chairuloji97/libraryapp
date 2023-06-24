package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.model.Book;

import java.util.List;

public interface BookService {

    //CREATE
    Book addBook(Book book);

    //READ
    List<Book> findAllByOrderByStockAsc();
    List<Book> findAllByOrderByTitleAsc();
    List<Book> findAllByOrderByReadingCountAsc();
    List<Book> findAllByOrderByWaitingListAsc();

    Book findById(Integer id);

    //DELETE
    void deleteById(Integer id);

    Book updateBook(Book book, Integer id);
}
