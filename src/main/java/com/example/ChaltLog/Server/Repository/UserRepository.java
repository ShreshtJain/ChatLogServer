package com.example.ChaltLog.Server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ChaltLog.Server.Entity.User;

public interface UserRepository  extends JpaRepository<User,String> {

}
