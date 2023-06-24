package id.co.indivara.jdt12.library.service;

import id.co.indivara.jdt12.library.model.Reader;

import java.util.Optional;

public interface ReaderService {
    Reader findById(Integer readerId);
}
