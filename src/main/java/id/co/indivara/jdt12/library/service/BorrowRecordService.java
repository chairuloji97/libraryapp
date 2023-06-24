package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.BorrowRecord;
import id.co.indivara.jdt12.library.model.Reader;

import java.util.Optional;

public interface BorrowRecordService {

    BorrowRecord findByBookAndReader(Book book, Reader reader);

    BorrowRecord createBorrowRecord(Book book, Reader reader);

    BorrowRecord updateRecordForReturnedBook(Book book, Reader reader);
}
