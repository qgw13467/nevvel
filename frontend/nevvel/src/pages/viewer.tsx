import React, { useEffect, useState } from "react";
import ViewHeader from "../components/viewer/ViewHeader";
import ViewerMain from "../components/viewer/ViewerMain";
import styled from "styled-components";
import { episode, content } from "editor";
import { useRouter } from "next/router";
import { atom } from "jotai";
import EpisodeData from "../components/viewer/DummyEpisodeData.json";

function viewer() {
  // const router = useRouter()
  // const { episode }:any=router.query
  // const postEpisode = JSON.parse(episode)
  // console.log(postEpisode)
  const [tabNumber, setTabNumber] = useState(0);
  const [headerToggle, setHeaderToggle] = useState(true);

  useEffect(()=>{
    setHeaderToggle(false)
  },[tabNumber])

  const clickhandler = (e: string) => {
    console.log(e)
    if (e === "head") {
      setHeaderToggle(!headerToggle);
    } else if (e === "block") {
      setTabNumber(tabNumber + 1);
      // 비동기 처리
    }
  };
  return (
    <ViewerWrapper>
      <HeaderContainer onClick={() => clickhandler("head")}>
        {headerToggle ? <ViewHeader EpisodeData={EpisodeData} /> : null}
      </HeaderContainer>
      <MainWrapper onClick={() => clickhandler("head")}>
        <MainContainer onClick={() => clickhandler("block")}>
          {tabNumber === 0 ? (
            <div>{EpisodeData.title}</div>
          ) : (
            <ViewerMain EpisodeData={EpisodeData} tabNumber={tabNumber} />
          )}
        </MainContainer>
      </MainWrapper>
    </ViewerWrapper>
  );
}

const ViewerWrapper = styled.div`
  display: flex;
  flex-direction: column;

  height: 100vh;
`;

const HeaderContainer = styled.div`
  display: flex;
  justify-content: center;
  height: 10vh;
`;
const MainWrapper = styled.div``;

const MainContainer = styled.div`
  height: 80vh;
  margin-left: 20%;
  margin-right: 20%;
  border: 1px solid black;
`;
export default viewer;
