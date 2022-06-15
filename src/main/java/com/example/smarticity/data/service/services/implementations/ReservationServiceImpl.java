package com.example.smarticity.data.service.services.implementations;

import com.example.smarticity.data.model.entity.IBaseInstitution;
import com.example.smarticity.data.model.entity.Reservation;
import com.example.smarticity.data.model.entity.enumer.InstituteType;
import com.example.smarticity.data.model.repository.BusinessRepository;
import com.example.smarticity.data.model.repository.HospitalRepository;
import com.example.smarticity.data.model.repository.HotelRepository;
import com.example.smarticity.data.model.repository.ReservationRepository;
import com.example.smarticity.data.service.models.ReservationServiceModel;
import com.example.smarticity.data.service.services.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final HospitalRepository hospitalRepository;
    private final BusinessRepository businessRepository;

    public ReservationServiceImpl(ModelMapper modelMapper, ReservationRepository reservationRepository, HotelRepository hotelRepository, HospitalRepository hospitalRepository, BusinessRepository businessRepository) {
        this.modelMapper = modelMapper;
        this.reservationRepository = reservationRepository;
        this.hotelRepository = hotelRepository;
        this.hospitalRepository = hospitalRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public ReservationServiceModel createReservation(ReservationServiceModel reservationServiceModel) {
        IBaseInstitution iBaseInstitution;
        if (reservationServiceModel.getInstituteType().equals(InstituteType.HOTEL)) {
            iBaseInstitution = hotelRepository.findById(reservationServiceModel.getInstituteId()).orElse(null);
        } else if (reservationServiceModel.getInstituteType().equals(InstituteType.BUSINESS)) {
            iBaseInstitution = businessRepository.findById(reservationServiceModel.getInstituteId()).orElse(null);
        } else if (reservationServiceModel.getInstituteType().equals(InstituteType.HOSPITAL)) {
            iBaseInstitution = hospitalRepository.findById(reservationServiceModel.getInstituteId()).orElse(null);
        } else {
            throw new IllegalArgumentException("This Institute Does Not Exist!");
        }

        if (iBaseInstitution == null) {
            throw new IllegalArgumentException("Institute Id Needs To Be An Existing Institute In Database!");
        }


        Reservation reservation = new Reservation(reservationServiceModel.getDescription(), reservationServiceModel.getStatus(), reservationServiceModel.getDateTo(), reservationServiceModel.getDateFrom()
                , reservationServiceModel.getInstituteType(), iBaseInstitution);
        this.reservationRepository.saveAndFlush(reservation);
        return this.modelMapper.map(this.reservationRepository.saveAndFlush(reservation), ReservationServiceModel.class);
    }
}
