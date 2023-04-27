import React, { useState, useEffect } from "react";
import styled from "styled-components";
import AssetCard from "../common/AssetCard";
import data from "./DummyAssetData_Image.json"

interface AssetTag {
  id : number,
  name : string,
}

interface Asset {
  id: number,
  title: string,
  type: string,
  thumbnail : string,
  url: string,
  tags: Array<AssetTag>,
}

function AssetstoreAssetList() {

  const [imgAsset, setImgAsset] = useState<Array<Asset>>([]);

  // axios로 데이터 get받아오기, 현재는 더미데이터
  useEffect(() => {
    setImgAsset(data.content);
  },[])
  console.log('이건데', imgAsset)



  return(
    <Wrapper>
      {
        imgAsset.map((imgAsset, index:number) => {
          return (
            <AssetCard
              key={index}
              id={imgAsset.id}
              title={imgAsset.title}
              type={imgAsset.type}
              thumbnail={imgAsset.thumbnail}
              url={imgAsset.url}
              tags={imgAsset.tags}
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