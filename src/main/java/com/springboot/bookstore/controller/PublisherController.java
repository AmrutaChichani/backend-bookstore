package com.springboot.bookstore.controller;

import com.springboot.bookstore.dto.PublisherDto;
import com.springboot.bookstore.entity.Publisher;
import com.springboot.bookstore.service.PublisherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/publisher")
public class PublisherController {

    @Autowired
    private PublisherServices publisherServices;

    @GetMapping
    public ResponseEntity<List<PublisherDto>> getPublishers() {
        List<PublisherDto> result=publisherServices.getPublishers();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{publisherId}")
    public ResponseEntity<PublisherDto> getPublisherById(@PathVariable("publisherId") Integer publisherId) {
        PublisherDto result=PublisherDto.from(publisherServices.getPublisherById(publisherId));
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<Integer> addOrUpdatePublisher(@Valid @RequestBody PublisherDto publisher) {
        Publisher thePublisher=new Publisher();
        if(publisher.getId()!=null)
            thePublisher.setId(publisher.getId());
        thePublisher.setName(publisher.getName());
        thePublisher.setContactNo(publisher.getContactNo());
        Integer result= publisherServices.addOrUpdatePublisher(thePublisher);
            return ResponseEntity.ok().body(result);
    }
}
