/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.repository;

import Caso1_JosueAlfaroCamacho.domain.Servicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
        List<Servicio> findByCategoriaId(Integer categoriaId);
 
}
