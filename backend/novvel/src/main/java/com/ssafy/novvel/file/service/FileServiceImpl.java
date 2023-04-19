package com.ssafy.novvel.file.service;

import com.madgag.gif.fmsware.GifDecoder;
import com.ssafy.novvel.file.repository.FileRepository;
import com.ssafy.novvel.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final AwsProxyService awsProxyService;
    private final FileRepository fileRepository;


    @Override
    public String fileSave(File file, Member member) {

        return null;
    }

    @Override
    public String findOriginByFileName(String fileName) {
        return null;
    }

    @Override
    public String findThumbnailByFileName(String fileName) {
        return null;
    }


    public File makeThumbnailFromGif(File file) throws IOException {

        log.info("tumbnail: {}", file.getName());

        InputStream targetStream = new FileInputStream(file);
        GifDecoder gifDecoder = new GifDecoder();
        File result = new File(file.getName()+"_thumnail.gif");
        gifDecoder.read(targetStream);
        BufferedImage image = gifDecoder.getFrame(0); // 인덱스에 해당하는 프레임 추출
        ImageIO.write(image,"gif",result);

        return result;

    }
}
