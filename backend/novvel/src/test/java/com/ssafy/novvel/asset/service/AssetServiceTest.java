package com.ssafy.novvel.asset.service;

import com.ssafy.novvel.asset.dto.AssetRegistDto;
import com.ssafy.novvel.asset.dto.AssetSearchDto;
import com.ssafy.novvel.asset.entity.Asset;
import com.ssafy.novvel.asset.entity.AssetTag;
import com.ssafy.novvel.asset.entity.AssetType;
import com.ssafy.novvel.asset.entity.Tag;
import com.ssafy.novvel.asset.repository.AssetRepository;
import com.ssafy.novvel.asset.repository.AssetTagRepository;
import com.ssafy.novvel.asset.repository.TagRepository;
import com.ssafy.novvel.resource.entity.Resource;
import com.ssafy.novvel.resource.service.ResourceService;
import com.ssafy.novvel.member.entity.Member;
import com.ssafy.novvel.util.TestUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class AssetServiceTest {

    @InjectMocks
    private AssetServiceImpl assetService;
    @Mock
    private AssetRepository assetRepository;
    @Mock
    private AssetTagRepository assetTagRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private ResourceService resourceService;


    @Test
    void addAssetTest() throws IOException {
        File file = new File("src/test/resources/test.gif");

        //input param
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), Files.probeContentType(file.toPath()), Files.readAllBytes(file.toPath()));
        Resource resource = TestUtil.getResource();

        AssetRegistDto assetRegistDto = AssetRegistDto.builder()
                .type(AssetType.IMAGE)
                .title("title")
                .description("desc")
                .point(10L)
                .tags(List.of("0", "호러"))
                .build();
        Member member = TestUtil.getMember();


        Asset asset = Asset.builder()
                .id(1L)
                .title(assetRegistDto.getTitle())
                .point(assetRegistDto.getPoint())
                .description(assetRegistDto.getDescription())
                .resource(resource)
                .member(member)
                .type(assetRegistDto.getType())
                .build();
        List<Tag> savedTagList = TestUtil.getTagList(2);
        Set<Tag> savedTagSet = Set.of(savedTagList.get(0));
        List<Tag> newTags = List.of(new Tag(3L, "호러"));
        Set<Tag> resultTagSet = Set.of(savedTagList.get(0), newTags.get(0));
        List<AssetTag> assetTags = new ArrayList<>();
        for (Tag tag : resultTagSet) {
            assetTags.add(new AssetTag(asset, tag));
        }

        //when
        Mockito.doReturn(resource).when(resourceService).saveFile(multipartFile);
        Mockito.doReturn(asset).when(assetRepository).save(Mockito.any());
        Mockito.doReturn(savedTagSet).when(tagRepository).findSetByTagNameIn(assetRegistDto.getTags());
        Mockito.doReturn(newTags).when(tagRepository).saveAll(Mockito.any());
        Mockito.doReturn(assetTags).when(assetTagRepository).saveAll(Mockito.any());

        Asset result = assetService.addAsset(multipartFile, member, assetRegistDto);

        //then
        Mockito.verify(tagRepository, Mockito.times(1)).saveAll(Mockito.any());
        Mockito.verify(tagRepository, Mockito.times(1)).findSetByTagNameIn(Mockito.any());
        Mockito.verify(assetTagRepository, Mockito.times(1)).saveAll(Mockito.any());
        Assertions.assertThat(result.getDescription()).isEqualTo(assetRegistDto.getDescription());
        Assertions.assertThat(result.getTitle()).isEqualTo(assetRegistDto.getTitle());
        Assertions.assertThat(result.getPoint()).isEqualTo(assetRegistDto.getPoint());
        Assertions.assertThat(result.getType()).isEqualTo(assetRegistDto.getType());
    }

    @Test
    void searchAssetByTagTest() {

        Asset asset1 = new Asset(1L, "title1", AssetType.IMAGE, "desc1", 100L);
        Asset asset2 = new Asset(2L, "title2", AssetType.IMAGE, "desc1", 100L);
        Asset asset3 = new Asset(3L, "title3", AssetType.IMAGE, "desc1", 100L);

        Tag tag1 = new Tag(1L, "tag1");
        Tag tag2 = new Tag(2L, "tag2");
        Tag tag3 = new Tag(3L, "tag3");

        List<AssetTag> findByAssetInResult = List.of(
                new AssetTag(asset1, tag1),
                new AssetTag(asset1, tag2),
                new AssetTag(asset2, tag2),
                new AssetTag(asset3, tag3)
        );

        List<Tag> findByTagNameInResult = List.of(tag1, tag2);
        Pageable pageable = PageRequest.of(0, 5);
        Slice<Asset> findByTagInResult = new SliceImpl<>(
                List.of(asset1, asset2),
                pageable,
                false
        );

        List<String> findTageNames = List.of("tag1", "tag2");

        //when
        Mockito.doReturn(findByTagNameInResult).when(tagRepository).findByTagNameIn(findTageNames);
        Mockito.doReturn(findByTagInResult).when(assetTagRepository).findByTagIn(findByTagNameInResult, pageable);
        Mockito.doReturn(findByAssetInResult).when(assetTagRepository).findByAssetIn(findByTagInResult.getContent());

        Slice<AssetSearchDto> assetSearchDtos = assetService.searchAssetByTag(findTageNames, pageable);

        //then
        Mockito.verify(tagRepository, Mockito.times(1)).findByTagNameIn(findTageNames);
        Mockito.verify(assetTagRepository, Mockito.times(1)).findByTagIn(findByTagNameInResult, pageable);
        Mockito.verify(assetTagRepository, Mockito.times(1)).findByAssetIn(findByTagInResult.getContent());
        Assertions.assertThat(assetSearchDtos.getContent().size()).isEqualTo(2);


    }


}
