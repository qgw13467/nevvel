import React from 'react'
import DummyAssetData_audio from "@/src/components/assetstore/DummyAssetData_Audio.json"
import styled from 'styled-components'
import { useAtomValue } from "jotai";
import { nowTextBlockAtom } from "@/src/store/EditorAssetStore";
import { Asset } from "editor";
import { content } from "editor";
import { event } from "editor";

type EditorMainAssetAudioListProps = {
  setContents: React.Dispatch<React.SetStateAction<content[]>>;
  contents: content[];
};

function EditorMainAssetSoundList({
  setContents,
  contents,
}: EditorMainAssetAudioListProps) {
  const assetData = DummyAssetData_audio
  const nowTextBlock = useAtomValue(nowTextBlockAtom);

  const ClickHandler = (asset: Asset) => {
    const newBlocks = [...contents];
    const index = newBlocks.findIndex((el) => el.idx === nowTextBlock);
    if (
      (newBlocks[index].event.length >= 2 )
    ) {
    } else {
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
  }
   setContents(newBlocks)

  };
 return (
    <AssetList>
      {assetData.content.map((asset,index)=>(
        <AssetItem key={index} onClick={() => ClickHandler(asset)}>
          <Img src={asset.thumbnail} 
          alt="썸네일" />
          {asset.title}
          </AssetItem>
      ))}
    </AssetList>
  )
}

const AssetList = styled.div`
  display: flex;
  flex-wrap:wrap;
  justify-content:flex-start;
  padding: 1rem;
  padding-top: 2rem;
  overflow: auto;
  position: relative;

`
const AssetItem = styled.div`
  color: ${({ theme})=> theme.color.text1};
  padding: 0.2rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 12px;
  overflow: hidden;
  text-overflow:ellipsis;
  width: 4.5rem;
  height: 5.5rem;
  white-space:nowrap;
  text-align: start;

`
const Img = styled.img`

  width: 4.5rem;
  height: 4.5rem;
`

export default EditorMainAssetSoundList