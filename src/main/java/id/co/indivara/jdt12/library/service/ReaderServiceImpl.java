package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.Exception.ResponseEntityErrorException;
import id.co.indivara.jdt12.library.model.Reader;
import id.co.indivara.jdt12.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReaderServiceImpl implements ReaderService{

    @Autowired
    ReaderRepository readerRepository;

    @Override
    public Reader findById(Integer readerId) {
        Optional<Reader> readerOptional = readerRepository.findById(readerId);
        if(!readerOptional.isPresent()){
            throw new ResponseEntityErrorException("Reader is not found", HttpStatus.NOT_FOUND);
        }

        return readerOptional.get();
    }
}
