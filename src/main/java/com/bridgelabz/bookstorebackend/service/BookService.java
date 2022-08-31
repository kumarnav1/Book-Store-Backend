package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.BookDTO;
import com.bridgelabz.bookstorebackend.model.BookData;
import com.bridgelabz.bookstorebackend.model.UserRegistration;
import com.bridgelabz.bookstorebackend.repository.IBookRepository;
import com.bridgelabz.bookstorebackend.service.serviceInterface.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBookRepository iBookService;

    @Override
    public BookData createBook(BookDTO bookDTO) {
        BookData bookData = modelMapper.map(bookDTO, BookData.class);
        System.out.println(bookData);
        return iBookService.save(bookData);
    }

    @Override
    public List<BookData> getAllBookData() {
        return iBookService.findAll();
    }

    @Override
    public BookData getBookDataById(int bookId) {
        return iBookService.findByBookId(bookId);
    }

    @Override
    public void deleteRecordByBookId(int bookId) {
        iBookService.deleteById(bookId);
    }

    @Override
    public BookData updateRecordById(Integer bookId, BookDTO bookDTO) {
        BookData bookServer = iBookService.findByBookId(bookId);
        modelMapper.map(bookDTO, bookServer);
        return iBookService.save(bookServer);
    }

    @Override
    public List<BookData> getBookByName(String bookName) {
        return iBookService.findByBookName(bookName);
    }

    @Override
    public List<BookData> getBookByNameAjax(String bookName) {
        return iBookService.findByBookNameContainingOrAuthorNameContaining(bookName, bookName);
    }

    @Override
    public List<BookData> sortedBooksInAscendingOrder() {
        return iBookService.getSortedListOfBooksInAsc();
    }

    @Override
    public List<BookData> sortedBooksInDescendingOrder() {
        return iBookService.getSortedListOfBooksInDesc();
    }
}