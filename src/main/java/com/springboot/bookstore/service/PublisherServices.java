package com.springboot.bookstore.service;

import com.springboot.bookstore.dao.PublishersDAO;
import com.springboot.bookstore.dto.PublisherDto;
import com.springboot.bookstore.entity.Publisher;
import com.springboot.bookstore.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PublisherServices {
    @Autowired
    private PublishersDAO publishersDao;

    public List<PublisherDto> getPublishers()
    {
        List<PublisherDto> publishers=new ArrayList<>();
        publishersDao.findAll().forEach(publisher->publishers.add(PublisherDto.from(publisher)));
        return publishers;

    }

    public Publisher getPublisherById(Integer publisherId)
    {
        Optional<Publisher> publisherOptional=publishersDao.findById(publisherId);
        if(!publisherOptional.isPresent()){
            log.info("Invalid publisher id:{}",publisherId);
            throw new BadRequestException();
        }
        return publisherOptional.get();

    }

    public Integer addOrUpdatePublisher(Publisher publisher)
    {
        publishersDao.save(publisher);
        return publisher.getId();
    }
}
