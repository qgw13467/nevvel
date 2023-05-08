import React,{useState} from "react";
import EditorHead from "@/src/components/editor/EditorHead";
import EditorMain from "@/src/components/editor/EditorMain";
import styled from "styled-components";
import { episode } from "editor";
import EditorMainAssetContainer from "@/src/components/editor/Main/Asset/EditorMainAssetContainer";
import { useAtomValue } from "jotai";
import { assetOpenAtom } from "@/src/store/EditorAssetStore";
import { mobile } from "@/src/util/Mixin";

function index() {
  const [episode, setEpisode] =useState<episode>({
    coverId:1,
    statusType:"PUBLISHED",
    point:0,
    title:"",
    contents:[]
  })
  const assetOpen = useAtomValue(assetOpenAtom)  

  return (<Wrapper>
    <EditorWrapper assetOpen={assetOpen}>
      <EditorHead 
      episode={episode}
      setEpisode={setEpisode}/>
      <EditorMain 
      setEpisode={setEpisode}
      episode={episode}/>
    </EditorWrapper>
    {assetOpen && <EditorMainAssetContainer />}
  </Wrapper>
  );
}
const Wrapper = styled.div`
  display: flex;
  color:white;
  flex-direction: row;
  ${mobile}{
    flex-direction: column;
  }
`

const EditorWrapper = styled.div<{assetOpen:number}>`
  background-color: ${({ theme }) => theme.color.background};
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 91vh;
  padding-left:${(props)=>(props.assetOpen ?(30):(20))}%;
  padding-right: ${(props)=>(props.assetOpen ?(15):(20))}%;

`;

export default index;
