package com.hp.service;


import com.hp.pojo.Live;
import org.springframework.web.multipart.MultipartFile;

public interface LiveService {
    int createRoom(String title, String name, String username, MultipartFile image, String crossfire);
    Live selectRoom(String username);
}
