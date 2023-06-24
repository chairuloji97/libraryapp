package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.model.Book;
import id.co.indivara.jdt12.library.model.Reader;
import id.co.indivara.jdt12.library.model.Wishlist;
import id.co.indivara.jdt12.library.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService{

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    BookServiceImpl bookServiceImpl;

    public void addWishlist(Book book, Reader reader) {

        boolean hasWishlisted = hasWishlisted(book, reader);
        if(hasWishlisted){
            return;
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setBook(book);
        wishlist.setReader(reader);
        wishlist.setAvailable(false);

        book.setWaitingList(book.getWaitingList()+1);
        bookServiceImpl.updateBook(book, book.getBookId());
        wishlistRepository.save(wishlist);
    }

    public boolean hasWishlisted(Book book, Reader reader) {
        Optional<Wishlist> hasWishlisted = wishlistRepository.findByBookAndReader(book,reader);
        return hasWishlisted.isPresent();
    }

    public void deleteWishlist(Integer wishlistId){
        wishlistRepository.deleteById(wishlistId);
    }

    @Override
    public boolean wishlistBorrowUpdate(Book book, Reader reader) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByBookAndReader(book,reader);

        if(wishlistOptional.isPresent()){
            deleteWishlist(wishlistOptional.get().getWishlistId());
            return true;
        }

        return false;
    }
}
