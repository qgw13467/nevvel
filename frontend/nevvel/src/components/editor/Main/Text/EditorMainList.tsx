import React, {
  useState,
  useRef,
  useEffect,
  Dispatch,
  SetStateAction,
} from "react";
import styled from "styled-components";
import EditorMainListItem from "./EditorMainListItem";
import EditorMainAssetContainer from "../Asset/EditorMainAssetContainer";
import { content } from "editor";
import EditorMainInput from "./EditorMainInput";

type EditorMainListProps = {
  contents: content[];
  setContents: React.Dispatch<React.SetStateAction<content[]>>;
  currentText: string;
  setCurrentText: Dispatch<SetStateAction<string>>;
};

function EditorMainList({
  contents,
  setContents,
  currentText,
  setCurrentText,
}: EditorMainListProps) {
  const scrollRef = useRef<any>();
  // useEffect(() => {
  //   console.log(scrollRef.current?.scrollHeight)
  //   scrollRef.current.scrollTop = (scrollRef.current?.scrollHeight + 64);
  // }, [currentText,contents]);

  return (
    <MainWrapper>
      <MainContainer ref={scrollRef}>
        <ListWrapper>
          {contents.map((content, index) => (
            <EditorMainListItem
              key={index}
              content={content}
              contents={contents}
              setContents={setContents}
            />
          ))}
        </ListWrapper>
        <InputWrapper>
          <EditorMainInput
            currentText={currentText}
            setCurrentText={setCurrentText}
            contents={contents}
            setContents={setContents}
          />
        </InputWrapper>
      </MainContainer>
    </MainWrapper>
  );
}

const MainWrapper = styled.div`
  /* overflow: scroll; */
`;
const MainContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  background-color: ${({ theme }) => theme.color.background};
  border-radius: 10px;
  /* box-shadow: 0px 0px 3px gray; */
  /* margin-bottom:10vh; */
`;

const ListWrapper = styled.div`
  /* border: 2px solid ${({ theme }) => theme.color.hover}; */
  height: auto;
  display: flex;
  flex-direction: column;
  width: 100%;
  ::-webkit-scrollbar {
    display: none;
  }
`;

const InputWrapper = styled.div`
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
`

const AssetWrapper = styled.div``;

const ItemContainer = styled.div``;
export default EditorMainList;
