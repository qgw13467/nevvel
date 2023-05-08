import React from 'react'
import EditorMainAssetImageList from './EditorMainAssetImageList'
import EditorMainAssetSoundList from './EditorMainAssetSoundList'
import styled from 'styled-components'
import { mobile } from '@/src/util/Mixin'
import { assetOpenAtom } from "@/src/store/EditorAssetStore";
import { useAtom } from 'jotai'


function EditorMainAssetContainer() {
  const [assetOpen, setAssetOpen] =useAtom(assetOpenAtom)

  return (<AssetContainer>
    <AssetHeaderContainer>
    <AssetStoreTitle>에셋스토어</AssetStoreTitle>
    <CloseBtn onClick={
      ()=>  setAssetOpen(0)
    }>X</CloseBtn>
    </AssetHeaderContainer>
  <div>{assetOpen === 1 && <EditorMainAssetImageList/>}</div>
  <div>{assetOpen === 2 && <EditorMainAssetSoundList/>}</div>
  </AssetContainer>
  )
}

const AssetContainer = styled.div`
  position: absolute;
  box-shadow: 0px 0px 2px gray;
  background-color: ${({ theme })=> theme.color.background};
  height: 100vh;
  width:20vw;
  ${mobile}{
    position: relative;
  }

`
const AssetStoreTitle = styled.div`
color: ${({ theme})=> theme.color.point};
height: 15%;
padding-left: 1rem;
padding-top:1rem;
  
`
const AssetHeaderContainer =styled.div`
  
`
const CloseBtn = styled.div`
  color:${({theme})=>theme.color.text1};
`

export default EditorMainAssetContainer  