/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.repository;

import Caso1_JosueAlfaroCamacho.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alfaro
 */
@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    
}