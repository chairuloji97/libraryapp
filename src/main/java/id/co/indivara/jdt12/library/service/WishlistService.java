package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.Reader;

public interface WishlistService {
    boolean wishlistBorrowUpdate(Book book, Reader reader);
}
