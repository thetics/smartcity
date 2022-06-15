package com.example.smarticity.data.service.services;

import com.example.smarticity.data.model.entity.Reservation;
import com.example.smarticity.data.service.models.ReservationServiceModel;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    ReservationServiceModel createReservation(ReservationServiceModel reservationServiceModel);
}
