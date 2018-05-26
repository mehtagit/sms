package com.sms.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sms.bean.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {

}
