package com.ssafy.novvel.file;

import com.ssafy.novvel.file.service.FileService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.IOException;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    @Disabled
    @DisplayName("get gif thumbnail img")
    void makeThumbnailFromGifTest() throws IOException {
        File file = new File("src/test/resources/test.gif");
        File result = fileService.makeThumbnailFromGif(file);

    }

    @Test
    @Disabled
    @DisplayName("convert gif to png")
    void convertGifToPngTest() throws IOException {
        File file = new File("src/test/resources/test_thumbnail.gif");
        File result = fileService.convertToPng(file);

    }

    @Test
    @Disabled
    @DisplayName("convert gif to jpg")
    void convertGifToJpgTest() throws IOException {
        File file = new File("src/test/resources/test_thumbnail.gif");
        File result = fileService.convertToJpg(file);

    }

    @Test
    @Disabled
    @DisplayName("convert jpg to png")
    void convertJpgToPngTest() throws IOException {
        File file = new File("src/test/resources/test.jpg");
        File result = fileService.convertToJpg(file);

    }

    @Test
    @Disabled
    @DisplayName("convert Resolution Png")
    void convertResolutionPngTest() throws IOException {
        File file = new File("src/test/resources/test_thumbnail.png");
        File result = fileService.convertResolutionPng(file, 200, 200);
    }

}
