package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.Exception.ResponseEntityErrorException;
import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        Optional<Book> bookExists = bookRepository.findByTitle(book.getTitle());
        if(bookExists.isPresent()){
            Book bookStock = bookExists.get();
            bookStock.setStock(bookStock.getStock()-1);
            return bookRepository.save(bookStock);
        } else {
            return bookRepository.save(book);
        }
    }

    @Override
    public List<Book> findAllByOrderByStockAsc() {
        List<Book> books = new ArrayList<>();
        books = bookRepository.findAllByOrderByStockAsc();
        return books;
    }

    @Override
    public List<Book> findAllByOrderByTitleAsc() {
        List<Book> books = new ArrayList<>();
        books = bookRepository.findAllByOrderByTitleAsc();
        return books;
    }

    @Override
    public List<Book> findAllByOrderByReadingCountAsc() {
        List<Book> books = new ArrayList<>();
        books = bookRepository.findAllByOrderByReadingCountAsc();
        return books;    }

    @Override
    public List<Book> findAllByOrderByWaitingListAsc() {

        List<Book> books = new ArrayList<>();
        books = bookRepository.findAllByOrderByWaitingListAsc();
        return books;
    }

    @Override
    public Book findById(Integer id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(!bookOptional.isPresent()){
            throw new ResponseEntityErrorException("Book not found", HttpStatus.NOT_FOUND);
        }

        return bookOptional.get();
    }

    @Override
    public void deleteById(Integer bookId) {
        Book book = findById(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book updateBook(Book book, Integer id) {
        Book bookUpdate = findById(book.getBookId());
        bookUpdate.setStock(book.getStock());
        bookUpdate.setReadingCount(book.getReadingCount());
        bookUpdate.setAuthor(book.getAuthor());
        bookUpdate.setTitle(book.getTitle());
        bookUpdate.setPages(book.getPages());
        bookUpdate.setWaitingList(book.getWaitingList());
        return bookRepository.save(bookUpdate);
    }

    public void updateReturnedBook(Integer bookId) {
        Book book = findById(bookId);

        book.setStock(book.getStock()+1);
        book.setReadingCount(book.getReadingCount()+1);
        book.setAvailable(true);

        bookRepository.save(book);
    }

    public void updateBorrowedBook(Book book) {
        Integer stock = book.getStock()-1;
        book.setStock(stock);
        book.setAvailable(stock != 0);
        bookRepository.save(book);
    }
}
