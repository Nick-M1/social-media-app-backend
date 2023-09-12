package com.nick.appmediaservice.service;

import com.nick.appmediaservice.model.Photo;
import com.nick.appmediaservice.repository.PhotoRepository;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;

    public Mono<Photo> getPhotoById(String id) {
        return photoRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException()));
    }

    public Mono<Photo> addPhoto(String title, MultipartFile file) throws IOException {
        var photo = new Photo(title, new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return photoRepository.save(photo);
    }

    public Mono<Void> deletePhoto(String title) {
        return photoRepository.deleteById(title);
    }
}
