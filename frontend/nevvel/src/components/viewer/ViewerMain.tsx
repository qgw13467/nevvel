import React from "react";
import { episode } from "editor";
import styled from "styled-components";

type viwerMainProps = {
  tabNumber:number
  EpisodeData: episode;
};
function ViewerMain({ EpisodeData,tabNumber }: viwerMainProps) {
  const contents = EpisodeData.contents;

  console.log(contents);
  return (<>
    <Container>
      {contents.map((content, index) => (
        <TextBlock key={index}>{content.idx <= tabNumber ? <>{content.context}</> : null}</TextBlock>
      ))}
    </Container>
  </>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  overflow-y:scroll;
  ::-webkit-scrollbar {
  display: none;
}
  `
const TextBlock = styled.div`
  padding: 0.5rem;
`

export default ViewerMain;
