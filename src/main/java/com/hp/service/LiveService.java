package com.hp.service;


import org.springframework.web.multipart.MultipartFile;

public interface LiveService {
    int createRoom(String title, String name, String username, MultipartFile image, String crossfire);
}
