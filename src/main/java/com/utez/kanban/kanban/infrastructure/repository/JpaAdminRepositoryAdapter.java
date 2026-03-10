package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.domain.port.out.AdminRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.AdminEntity;
import com.utez.kanban.kanban.infrastructure.mapper.AdminMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAdminRepositoryAdapter implements AdminRepositoryPort {

    private final JpaAdminRepository jpaAdminRepository;

    public JpaAdminRepositoryAdapter(JpaAdminRepository jpaAdminRepository){
        this.jpaAdminRepository = jpaAdminRepository;
    }


    @Override
    public void createAdmin(Admin admin) {
        jpaAdminRepository.save(AdminMapper.toAdminEntity(admin));
    }

    @Override
    public Optional<Admin> findById(Long adminID) {
        return jpaAdminRepository.findById(adminID)
                .map(AdminMapper::toAdmin);
    }

    @Override
    public Optional<Admin> update(Admin admin) {
        if(jpaAdminRepository.existsById(admin.getAdminID())){
            AdminEntity adminEntity = jpaAdminRepository.save(AdminMapper.toAdminEntity(admin));
            return Optional.of(AdminMapper.toAdmin(adminEntity));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long adminID) {
        if(jpaAdminRepository.existsById(adminID)) {
            jpaAdminRepository.deleteById(adminID);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return jpaAdminRepository.findAdminByEmail(email)
                .map(AdminMapper::toAdmin );
    }


}
