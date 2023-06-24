package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    BookServiceImpl bookServiceImpl;

    public List<Book> displayBooks(String orderBy) {
        List<Book> books = new ArrayList<>();
        if(orderBy == null || orderBy.isEmpty()){
            books = bookServiceImpl.findAllByOrderByTitleAsc();
        } else if(orderBy.equals("stock")){
            books = bookServiceImpl.findAllByOrderByStockAsc();
        } else if(orderBy.equals("waitinglist")){
            books = bookServiceImpl.findAllByOrderByWaitingListAsc();
        } else if(orderBy.equals("readingcount")){
            books = bookServiceImpl.findAllByOrderByReadingCountAsc();
        } else {
            bookServiceImpl.findAllByOrderByTitleAsc();
        }
        return books;
    }
}
