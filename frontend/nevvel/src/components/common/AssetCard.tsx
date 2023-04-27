import React, { useState } from "react";
import styled from "styled-components";

interface AssetTag {
  id : number,
  name : string,
}

interface Asset {
  id: number,
  title: string,
  type: string,
  thumbnail : string,
  url: string,
  tags: Array<AssetTag>,
}

function AssetCard({
  id,
  title,
  type,
  thumbnail,
  url,
  tags,
} : Asset) {

  const [hoverTrigger, setHoverTrigger] = useState(0)

  const Trigger1 = () => {
    setHoverTrigger(1)
  }
  const Trigger2 = () => {
    setHoverTrigger(0)
  }

  return(
    <Wrapper>
      {
        hoverTrigger === 0?
        <CardImg src={thumbnail} alt="썸네일" onMouseOver={Trigger1} />:
        <CardImg src={url} alt="썸네일" onMouseLeave={Trigger2} />
      }
      <br />
      {title}
      <br />
      {tags[0].name}
    </Wrapper>
  )
}

export default AssetCard

const Wrapper = styled.div`
  background-color: ${({ theme }) => theme.color.background};
  /* flex-direction: column; */
  width:30%;
  height: 50%;
  margin-top: 1%;
  margin-bottom: 1%;
`;

const CardImg = styled.img`
  width: 100%;
  height: 100%;
`