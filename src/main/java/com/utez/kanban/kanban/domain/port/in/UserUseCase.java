package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.User;

public interface UserUseCase {
    User createUser(User user);
}
