package com.bridgelabz.bookstorebackend.service;

import com.bridgelabz.bookstorebackend.dto.DeliveryDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.Delivery;
import com.bridgelabz.bookstorebackend.repository.IDeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Service
public class DeliveryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IDeliveryRepository iDeliveryRepository;


    public Delivery addDeliveryDetails(DeliveryDTO deliveryDTO) {
        Delivery deliveryDetails = modelMapper.map(deliveryDTO, Delivery.class);
        iDeliveryRepository.save(deliveryDetails);
        return deliveryDetails;
    }


    public List<Delivery> getAllDeliveryDetails() {
        List<Delivery> DeliveryDetails = iDeliveryRepository.findAll();
        return DeliveryDetails;
    }

    public Delivery deleteDeliveryDetails(Integer id) {
        iDeliveryRepository.deleteAll();
        return null;
    }

}
