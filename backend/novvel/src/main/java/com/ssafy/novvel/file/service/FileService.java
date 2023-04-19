package com.ssafy.novvel.file.service;

import com.ssafy.novvel.member.entity.Member;

import java.io.File;
import java.io.IOException;

public interface FileService {
    String fileSave(File file, Member member);

    String findOriginByFileName(String fileName);

    String findThumbnailByFileName(String fileName);

    File makeThumbnailFromGif(File file) throws IOException;


}
