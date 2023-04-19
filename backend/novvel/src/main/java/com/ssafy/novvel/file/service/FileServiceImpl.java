package com.ssafy.novvel.file.service;

import com.madgag.gif.fmsware.GifDecoder;
import com.ssafy.novvel.file.repository.FileRepository;
import com.ssafy.novvel.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
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

        log.info("makeThumbnailFromGif: {}", file.getName());

        InputStream targetStream = new FileInputStream(file);
        GifDecoder gifDecoder = new GifDecoder();
        // 파일 이름 가져오기
        String fileName = getFineName(file);
        File result = new File(fileName + "_thumbnail.gif");
        gifDecoder.read(targetStream);
        BufferedImage image = gifDecoder.getFrame(0); // 인덱스에 해당하는 프레임 추출
        ImageIO.write(image, "gif", result);

        return result;

    }

    @Override
    public File convertToPng(File file) throws IOException {
        File inputFile = file;
        String fileName = getFineName(file);
        log.info("convertToPng: {}", fileName);
        File outputFile = new File(fileName + ".png");
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        ImageIO.write(bufferedImage, "png", outputFile);
        return outputFile;
    }

    @Override
    public File convertToJpg(File file) throws IOException {
        File inputFile = file;
        String fileName = getFineName(file);
        log.info("convertToPng: {}", fileName);
        File outputFile = new File(fileName + ".jpg");
        BufferedImage bufferedImage = ImageIO.read(inputFile);
        ImageIO.write(bufferedImage, "jpg", outputFile);
        return outputFile;
    }


    @Override
    public  File convertResolutionPng(File file, int newWidth, int newHeight) throws IOException {
        File inputFile = file;
        String fileName = getFineName(file);
        File outputFile = new File(fileName+"_resolution.png");

        BufferedImage inputImage = ImageIO.read(inputFile);
//        int width = inputImage.getWidth();
//        int height = inputImage.getHeight();


        // 새로운 BufferedImage 객체를 생성합니다.
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, inputImage.getType());

        // Graphics2D 객체를 가져옵니다.
        Graphics2D g2d = outputImage.createGraphics();

        // RenderingHints를 설정합니다.
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // 새로운 해상도로 이미지를 그립니다.
        g2d.drawImage(inputImage, 0, 0, newWidth, newHeight, null);

        // Graphics2D 객체를 해제합니다.
        g2d.dispose();

        // 새로운 이미지를 파일로 출력합니다.
        ImageIO.write(outputImage, "png", outputFile);

        return outputFile;
    }


    private String getFineName(File file) {
        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        if (index > 0 && index < fileName.length() - 1) {
            fileName = fileName.substring(0, index);
        }
        return fileName;
    }
}
