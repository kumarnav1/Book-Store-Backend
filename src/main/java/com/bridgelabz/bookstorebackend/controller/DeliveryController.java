package com.bridgelabz.bookstorebackend.controller;


import com.bridgelabz.bookstorebackend.dto.DeliveryDTO;
import com.bridgelabz.bookstorebackend.dto.ResponseDTO;
import com.bridgelabz.bookstorebackend.model.Delivery;
import com.bridgelabz.bookstorebackend.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@CrossOrigin
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addDeliveryDetails(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        Delivery deliveryDetails = deliveryService.addDeliveryDetails(deliveryDTO);
        ResponseDTO responseDTO = new ResponseDTO("delivery Details updated Successfully", deliveryDetails);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Delivery> getAllDeliveryDetails() {
        List<Delivery> responseDTO = deliveryService.getAllDeliveryDetails();
        return responseDTO;
    }

    @DeleteMapping("/deleteDeliveryDetails/{id}")
    public ResponseEntity<ResponseDTO> deleteDeliveryDetails(@PathVariable Integer id) {
        Delivery deliveryDetails = deliveryService.deleteDeliveryDetails(id);
        ResponseDTO dto = new ResponseDTO("Record deleted successfully !", deliveryDetails);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

}
