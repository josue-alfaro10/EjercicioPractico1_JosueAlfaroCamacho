/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Caso1_JosueAlfaroCamacho.service;

import Caso1_JosueAlfaroCamacho.domain.Reserva;
import Caso1_JosueAlfaroCamacho.repository.ReservaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getReservas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> getReserva(Integer id) {
        return reservaRepository.findById(id);
    }

    public void guardar(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    public void eliminar(Integer id) {
        reservaRepository.deleteById(id);
    }
}
