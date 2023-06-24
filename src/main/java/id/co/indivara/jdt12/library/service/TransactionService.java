package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.Exception.ResponseEntityErrorException;
import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.BorrowRecord;
import id.co.indivara.jdt12.library.model.Reader;
import id.co.indivara.jdt12.library.model.Wishlist;
import id.co.indivara.jdt12.library.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @Autowired
    WishlistServiceImpl wishlistServiceImpl;

    @Autowired
    ReaderServiceImpl readerServiceImpl;

    @Autowired
    BorrowRecordServiceImpl borrowRecordServiceImpl;

    public BorrowRecord borrowBook(Integer bookId, Integer readerId) {
        try {
            Book book = bookServiceImpl.findById(bookId);
            Reader reader = readerServiceImpl.findById(readerId);
            boolean alreadyBorrowed = borrowRecordServiceImpl.alreadyBorrowed(book,reader);
            if(alreadyBorrowed){
                throw new ResponseEntityErrorException("You already borrow this book!", HttpStatus.BAD_REQUEST);
            }

            //CEK JIKA STOK BUKU TERSEDIA: JIKA TIDAK, MAKA MASUKKAN KE WISHLIST
            Integer stock = book.getStock();
            if(stock <= 0){
                wishlistServiceImpl.addWishlist(book, reader);
                throw new ResponseEntityErrorException("Stock is empty for now. Putting book on your wishlist!", HttpStatus.NOT_FOUND);

            }

            //CEK JIKA SUDAH PERNAH MASUK WISHLIST
            boolean wishlistUpdated = wishlistServiceImpl.wishlistBorrowUpdate(book,reader);
            if(wishlistUpdated){
                book.setWaitingList(book.getWaitingList()-1);
            }

            //UPDATE STOCK BUKU
            bookServiceImpl.updateBorrowedBook(book);

            //CREATE RECORD PEMINJAMAN BUKU BARU
            return borrowRecordServiceImpl.createBorrowRecord(book, reader);
        } catch (ResponseEntityErrorException e){
            throw new ResponseEntityErrorException(e.getMessage(),e.getStatusCode());
        }
    }

    public BorrowRecord returnBook(Integer bookId, Integer readerId) {
        BorrowRecord borrowRecord;
        try {
            Book book = bookServiceImpl.findById(bookId);
            Reader reader = readerServiceImpl.findById(readerId);

            //UPDATE RECORD PEMINJAMAN BUKU DAN DENDA
            borrowRecord = borrowRecordServiceImpl.updateRecordForReturnedBook(book,reader);

            //UPDATE DATA KETERSEDIAAN BUKU YANG TELAH DIKEMBALIKAN
            bookServiceImpl.updateReturnedBook(bookId);

            return borrowRecord;
        } catch (ResponseEntityErrorException e){
            throw new ResponseEntityErrorException(e.getMessage(),e.getStatusCode());
        }
    }
}
