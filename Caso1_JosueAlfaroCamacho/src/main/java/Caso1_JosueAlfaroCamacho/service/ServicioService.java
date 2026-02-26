/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Caso1_JosueAlfaroCamacho.service;

import Caso1_JosueAlfaroCamacho.domain.Categoria;
import Caso1_JosueAlfaroCamacho.domain.Servicio;
import Caso1_JosueAlfaroCamacho.repository.CategoriaRepository;
import Caso1_JosueAlfaroCamacho.repository.ServicioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
public class ServicioService {
 
    @Autowired
    private ServicioRepository servicioRepository;
 
    @Autowired
    private CategoriaRepository categoriaRepository;
 
    @Transactional(readOnly = true)
    public List<Servicio> getServicios() {
        return servicioRepository.findAll();
    }
 
    @Transactional(readOnly = true)
    public Optional<Servicio> getServicio(Integer id) {
        return servicioRepository.findById(id);
    }
 
    @Transactional
    public void guardar(Servicio servicio) {
        if (servicio.getCategoria() != null && servicio.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository
                    .findById(servicio.getCategoria().getId())
                    .orElse(null);
            servicio.setCategoria(categoria);
        }
        servicioRepository.save(servicio);
    }
 
    @Transactional
    public void eliminar(Integer id) {
        if (!servicioRepository.existsById(id)) {
            throw new IllegalArgumentException("El servicio con ID " + id + " no existe.");
        }
        try {
            servicioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("No se puede eliminar. Tiene datos asociados.", e);
        }
    }
}
