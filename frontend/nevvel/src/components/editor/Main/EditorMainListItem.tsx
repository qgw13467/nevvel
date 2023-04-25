import React, { useState } from "react";
import styled from "styled-components";

interface TextBlock {
  id: number;
  text: string;
  image: string;
  sound: string;
}

type EditorMainListItemProps = {
  block: TextBlock;
  textBlocks: TextBlock[];
  setTextBlocks: React.Dispatch<React.SetStateAction<TextBlock[]>>;
};

function EditorMainListItem({ block,textBlocks,setTextBlocks}: EditorMainListItemProps) {
  const [plus, setPlus] = useState(false);

  return (
    <BlockContainer>
      {plus ? (
        <>
          <AssetButton onClick={() => setPlus(!plus)}>-</AssetButton>
          <div>{block.image}</div>
          <div>{block.sound}</div>
        </>
      ) : (
        <AssetButton onClick={() => setPlus(!plus)}>+</AssetButton>
      )}
      <TextBlock>{block.text}</TextBlock>
      <RemoveButton onClick={()=>setTextBlocks(textBlocks.filter((block)=>block.id !== block.id))}>X</RemoveButton>
    </BlockContainer>
  );
}

const BlockContainer = styled.div`
  color: ${({ theme }) => theme.color.text1};
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 0.5rem;
  margin-bottom: 0.5rem;
  width: 100%;
`;
const AssetButton = styled.button`
  margin-left: 1rem;
  display: flex;
  text-align: center;
  align-items: center;
`;

const TextBlock = styled.div`
  width: 100%;
  border-radius: 10px;
  background-color: ${({ theme }) => theme.color.editor};
  height: 3rem;
  padding-left: 1rem;
  display: flex;
  align-items: center;
`;

const RemoveButton = styled.button``;

export default EditorMainListItem;
