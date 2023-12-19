package com.vinibortoletto.simpleshop.services;

import com.vinibortoletto.simpleshop.dtos.admin.AdminRequestDTO;
import com.vinibortoletto.simpleshop.exceptions.DatabaseException;
import com.vinibortoletto.simpleshop.exceptions.NotFoundException;
import com.vinibortoletto.simpleshop.models.Admin;
import com.vinibortoletto.simpleshop.models.User;
import com.vinibortoletto.simpleshop.repositories.AdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin findById(String id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElseThrow(() -> new NotFoundException("Admin not found"));
    }

    public Admin save(User user) {
        Admin admin = new Admin();

        admin.setUser(user);
        admin.setName(user.getName());
        admin.setEmail(user.getEmail());

        return adminRepository.save(admin);
    }

    public Admin update(AdminRequestDTO dto, String id) {
        Admin admin = findById(id);
        BeanUtils.copyProperties(dto, admin);
        return adminRepository.save(admin);
    }

    public void delete(String id) {
        findById(id);

        try {
            adminRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
