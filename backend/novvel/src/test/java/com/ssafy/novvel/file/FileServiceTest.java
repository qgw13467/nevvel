package com.ssafy.novvel.file;

import com.ssafy.novvel.file.service.FileService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    @Disabled
    @DisplayName("get gif thumbnail img")
    void makeThumbnailFromGifTest() throws IOException {

        File file = new File("src/test/resources/test.gif");
        System.out.println("start");
        File result = fileService.makeThumbnailFromGif(file);
        byte[] fileContent = Files.readAllBytes(result.toPath());
        FileOutputStream fileOutputStream = new FileOutputStream(result);
        System.out.println("check");
        fileOutputStream.write(fileContent);
        fileOutputStream.flush();
        fileOutputStream.close();

    }

}
