package com.tdtu.library.service;

import com.tdtu.library.dto.AdminDto;
import com.tdtu.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
}
