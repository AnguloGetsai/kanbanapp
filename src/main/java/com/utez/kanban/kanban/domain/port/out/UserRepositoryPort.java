package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.User;

public interface UserRepositoryPort {
    User saveUser(User user);
}
