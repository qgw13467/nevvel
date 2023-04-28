import React, { useState, useEffect } from "react";
import styled from "styled-components";
import AssetCard from "../common/AssetCard";
import imgdata from "./DummyAssetData_Image.json"
import sounddata from "./DummyAssetData_Audio.json"

interface AssetTag {
  id : number,
  name : string,
}

interface AssetUploader {
  id : number,
  nickname : string,
  profileImage : string,
}

interface Asset {
  id: number,
  title: string,
  type: string,
  thumbnail : string,
  url: string,
  price : number,
  downloadCount : number,
  tags: Array<AssetTag>,
  uploader : AssetUploader
}

function AssetstoreAssetList() {

  const [AssetData, setAssetData] = useState<Array<Asset>>([]);

  // axios로 데이터 get받아오기, 현재는 더미데이터
  useEffect(() => {
    setAssetData(imgdata.content);
    // setAssetData(sounddata.content);
  },[])
  console.log('이건데', AssetData)



  return(
    <Wrapper>
      {
        AssetData.map((AssetData, index:number) => {
          return (
            <AssetCard
              key={index}
              id={AssetData.id}
              title={AssetData.title}
              type={AssetData.type}
              thumbnail={AssetData.thumbnail}
              url={AssetData.url}
              tags={AssetData.tags}
            />
          )
        })
      }
    </Wrapper>
  )
}

export default AssetstoreAssetList

const Wrapper = styled.div`
  background-color: ${({ theme }) => theme.color.background};
  display: flex;
  justify-content: space-between;
  /* flex-direction: column; */
  width: 100%;
  height: 100vh;
  padding-top: 1%;
  padding-bottom: 1%;
  padding-left: 7%;
  padding-right: 7%;
`;