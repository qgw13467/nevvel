import React from "react";
import styled from "styled-components";
import { episode } from "editor";
import { AiFillCaretLeft, AiFillCaretRight } from "react-icons/ai";
type ViewHeaderProps = {
  EpisodeData: episode;
};

function ViewHeader({ EpisodeData }: ViewHeaderProps) {
  return (
    <HeaderContainer>
        <AiFillCaretLeft />
      <EpisodeTitle>ViewHeader</EpisodeTitle>
      <AiFillCaretRight />
    </HeaderContainer>
  );
}
const HeaderContainer = styled.div`
  width: 80%;
  display: flex;
  justify-content: space-between;
  text-align: center;
  align-items: center;
`;
const EpisodeTitle = styled.div``;

export default ViewHeader;
