import React, { useCallback, useEffect, useState } from "react";
import styled from "styled-components";
import { episode } from "editor";
import { useRouter } from "next/router";
import { Modal } from "../common/Modal";
import EditorPreview from "./Head/EditorPreview";
import { AiFillCheckCircle } from "react-icons/ai";

type EditorHeadProps = {
  setEpisode: React.Dispatch<React.SetStateAction<episode>>;
  episode: episode;
};

function EditorHead({ episode, setEpisode }: EditorHeadProps) {
  const router = useRouter();
  const [postEpisode, setPostEpisode] = useState<episode>();
  const [modalOpen, setModalOpen] = useState(false);
  const [saveToast, setSaveToast] = useState(false);
  useEffect(() => {
    console.log(episode);
  }, [episode]);

  useEffect(() => {
    if (saveToast) {
      setTimeout(() => setSaveToast(false), 2000);
    }
  }, [saveToast]);

  const Titlehandler = (e: any) => {
    setEpisode({
      ...episode,
      [e.target.name]: e.target.value,
    });
  };

  const PublishHandler = () => {
    setPostEpisode(episode);
    if (postEpisode) {
      router.push({
        pathname: "/viewer/[id]",
        query: { id: 1 },
      });
    }
  };

  const saveHandler = () => {
    // 임시저장 axios 연결하면 제대로 해보기..
    setSaveToast(true);
  };

  return (
    <Wrapper>
      <ButtonWrapper>
        <WriteButtonContainer>
          <WriteButton onClick={() => setModalOpen(true)}>미리보기</WriteButton>
          <WriteButton onClick={saveHandler}>임시저장</WriteButton>
          <WriteButton onClick={PublishHandler}>발행하기</WriteButton>
        </WriteButtonContainer>
      </ButtonWrapper>
      <InputWrapper>
        <TitleInput
          value={episode.title}
          onChange={Titlehandler}
          name="title"
          placeholder="에피소드 명을 입력하세요"
        />
      </InputWrapper>
      <BackGroundAssetContainer>전체 에셋</BackGroundAssetContainer>
      {modalOpen && (
        <Modal
          modal={modalOpen}
          setModal={setModalOpen}
          width="1200"
          height="600"
          element={
            <div>
              <EditorPreview postEpisode={episode} />
            </div>
          }
        />
      )}
      {saveToast && (
        <ToastContainer>
          <AiFillCheckCircle />
          임시저장 되었습니다.
        </ToastContainer>
      )}
    </Wrapper>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding-top: 3rem;
`;
const ButtonWrapper = styled.div`
  display: flex;
  justify-content: flex-end;
`;

const WriteButtonContainer = styled.div``;

const WriteButton = styled.button`
  color: ${({ theme }) => theme.color.point};
  border: 3px solid ${({ theme }) => theme.color.point};
  margin: 1px;
  border-radius: 10px;
  height: 2.75rem;
  width: 5rem;
  font-weight: 700;
`;
const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const TitleInput = styled.input`
  height: 2rem;
  font-size: 1.5rem;
  margin-top: 1rem;
  margin-bottom: 1rem;
  padding-left: 1rem;
  /* background-color: ${({ theme }) => theme.color.hover}; */
  padding: 1.5rem;
  border: none;
  border-radius: 10px;
  font-size: 16px;
    box-shadow: 0px 0px 3px gray;
  
  ::placeholder {
    font-size: 1rem;
  }
`;
const BackGroundAssetContainer = styled.div`
 box-shadow: 0px 0px 3px gray;
 padding: 1rem;
 margin-bottom: 1rem;
 border-radius: 10px;
`

const ToastContainer = styled.div`
  position: fixed;
  background-color: ${({ theme }) => theme.color.point};
  width: 10rem;
  height: 2rem;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0px 0px 0.5rem gray;
  border-radius: 0.5rem;
  transition: 0.1s ease-in;
  animation: slidein--bottom 0.5s;

  @keyframes slidein--bottom {
    0% {
      opacity: 0;
      transform: translateY(100%);
    }
    100% {
      opacity: 1;
      transform: translateY(0);
    }
  }
`;

export default EditorHead;
