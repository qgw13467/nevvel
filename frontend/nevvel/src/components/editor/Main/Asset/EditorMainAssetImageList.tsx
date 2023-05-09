import React, { useEffect } from "react";
import DummyAssetData_image from "@/src/components/assetstore/DummyAssetData_Image.json";
import styled from "styled-components";
import AssetCard from "@/src/components/common/AssetCard";
import { useAtomValue } from "jotai";
import { nowTextBlockAtom } from "@/src/store/EditorAssetStore";
import { Asset } from "editor";
import { content } from "editor";
import { event } from "editor";

type EditorMainAssetImageListProps = {
  setContents: React.Dispatch<React.SetStateAction<content[]>>;
  contents: content[];
};

function EditorMainAssetImageList({
  setContents,
  contents,
}: EditorMainAssetImageListProps) {
  const assetData = DummyAssetData_image;
  const nowTextBlock = useAtomValue(nowTextBlockAtom);

  useEffect(()=>{
    console.log(contents)
  },[contents])
  
  const ClickHandler = (asset: Asset) => {
    const newBlocks = [...contents];
    const index = newBlocks.findIndex((el) => el.idx === nowTextBlock);
    
    if (newBlocks[index].event.length !== 0){
      newBlocks[index].event.push({
        assetId: asset.id,
        type: asset.type
      }) 
    }
    else {
      newBlocks[index].event.push({
        assetId: asset.id,
        type: asset.type
      }) 
    }
   setContents(newBlocks)

  };
  return (
    <AssetList>
      {assetData.content.map((asset, index) => (
        <AssetItem key={index} onClick={() => ClickHandler(asset)}>
          <Img src={asset.thumbnail} alt="썸네일" />
        </AssetItem>
      ))}
    </AssetList>
  );
}

const AssetList = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  padding: 1rem;
  padding-top: 2rem;
  overflow: auto;
  position: relative;
`;
const AssetItem = styled.button`
  color: ${({ theme }) => theme.color.text1};
  padding: 0.2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
`;
const Img = styled.img`
  width: 4.5rem;
  height: 4.5rem;
`;

export default EditorMainAssetImageList;
