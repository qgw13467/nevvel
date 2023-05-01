import React, { useRef, useState } from "react";
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

  const audioRef = useRef<any>(null)


  const [hoverTrigger, setHoverTrigger] = useState(0)

  const ImgTriggerOn = () => {
    setHoverTrigger(1)
  }
  const ImgTriggerOff = () => {
    setHoverTrigger(0)
  }
  const AudTriggerOn = () => {
    setHoverTrigger(1)
    audioRef.current.play()    
  }
  const AudTriggerOff = () => {
    setHoverTrigger(0)
    audioRef.current.pause() 
  }

  return(
    
    <Wrapper>
      <CardImgDiv>
        <audio ref={audioRef} src={`${url}`} />
        {
          type === "AUDIO"?
          (
            hoverTrigger === 0?
            <CardImg src="https://cdn4.iconfinder.com/data/icons/proglyphs-multimedia/512/Volume_Off-512.png" alt="썸네일" onMouseOver={AudTriggerOn} />
            :
            <CardImg src={thumbnail} alt="썸네일" onMouseLeave={AudTriggerOff} />
          )
          :
          (
            hoverTrigger === 0?
            <CardImg src={thumbnail} alt="썸네일" onMouseOver={ImgTriggerOn} />:
            <CardImg src={url} alt="썸네일" onMouseLeave={ImgTriggerOff} />
          )
        }
      </CardImgDiv>
      <CardInfo1>{title}</CardInfo1>
      <CardInfo2>{tags[0].name}</CardInfo2>
    </Wrapper>
  )
}

export default AssetCard


const Wrapper = styled.div`
  background-color: ${({ theme }) => theme.color.background};
  /* flex-direction: column; */
  width: 20%;
  height: 100%;
  margin-top: 1%;
  margin-bottom: 5%;
`;

const CardImgDiv = styled.div`
  display: flex;
  justify-content: center;
  margin-left: 2.5%;
  margin-right: 2.5%;
`

const CardImg = styled.img`
  width: 100%;
  height: 35vh;
  border-radius: 5%;
`
const CardInfo1 = styled.p`
  color: ${({ theme }) => theme.color.text1};
  width: 100%;
  height: 2rem;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-top: 5%;
  font-size: 150%;
  text-align: center;
  /* margin-left: 2.5%; */
  /* margin-right: 2.5%; */
`

const CardInfo2 = styled.p`
  color: ${({ theme }) => theme.color.text1};
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-top: 5%;
  font-size: 120%;
  display: flex;
  justify-content: left;
  margin-left: 10%;
  
`