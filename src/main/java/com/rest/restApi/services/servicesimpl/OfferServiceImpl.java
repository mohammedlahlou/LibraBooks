package com.rest.restApi.services.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.rest.restApi.entities.Offer;
import com.rest.restApi.exceptions.OfferNotFoundException;
import com.rest.restApi.reposotiry.OfferRepository;
import com.rest.restApi.services.BookService;
import com.rest.restApi.services.OfferService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OfferServiceImpl implements OfferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferServiceImpl.class);

    @Autowired
    OfferRepository repository;

    @Autowired
    BookService bookService;

    @Override
    public Offer save(@NotNull @Valid Offer offer) {
        LOGGER.info("Creating {} ", offer);
        return repository.save(offer);
    }

    @Override
    public List<Offer> getAll() {
        LOGGER.info("Retrieve All Offer");

        return repository.findAll();
    }

    @Override
    public Offer getById(Long id) throws OfferNotFoundException {
        LOGGER.info("Retrieve Offer By Id = {} ", id);

        if (repository.existsById(id))
            return repository.getById(id);
        throw new OfferNotFoundException("No Offer with Id = " + id);
    }

    @Override
    public void deleteById(Long id) throws OfferNotFoundException {
        LOGGER.info("Delete Offer by Id = ", id);
        if (!repository.existsById(id))
            throw new OfferNotFoundException("No Offer with Id = " + id);
        repository.deleteById(id);

    }

    @Override
    public Offer update(Long id, Offer offer) throws OfferNotFoundException {
        LOGGER.info("Update Offer id= {} ", id);

        return repository.findById(id).map(x -> {
            x.setAddress(offer.getAddress());
            x.setCreated_at(offer.getCreated_at());
            x.setFinish_at(offer.getFinish_at());
            x.setBooks(offer.getBooks());
            x.setUser(offer.getUser());
            return repository.save(x);
        }).orElseThrow(() -> new OfferNotFoundException(String.format("Offer  With id = %s don't exist", id)));
    }

    @Override
    public List<Offer> findByAddress(String address) {
        LOGGER.info("Retrieve Offers By Address  {} ", address);
        List<Offer> findByAddress = new ArrayList<>();
        repository.findAll().forEach((offer)->{
            if(offer.getAddress().startsWith(address.toUpperCase()))
                    findByAddress.add(offer);
        });
        return findByAddress;
    }

    @Override
    public List<Offer> findByBookTitle(String title) {
        LOGGER.info("Retrieve Offers By Book Title  {} ", title);

        List<Offer> offers = new ArrayList<>();
        repository.findAll().forEach((offer) -> {
            offer.getBooks().forEach((book) -> {
                if (book.getTitle().startsWith(title.toUpperCase()))
                    offers.add(offer);
            });
        });

        return offers;
    }

    @Override
    public List<Offer> findByBookAuthor(String author) {
        LOGGER.info("Retrieve Offers By Book Author  {} ", author);

        List<Offer> offers = new ArrayList<>();
        repository.findAll().forEach((offer) -> {
            offer.getBooks().forEach((book) -> {
                if (book.getAuthor().startsWith(author.toUpperCase()))
                    offers.add(offer);
            });
        });

        return offers;
    }

}