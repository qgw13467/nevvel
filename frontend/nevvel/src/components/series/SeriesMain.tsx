import React from 'react'
import styled from 'styled-components'
import SeriesMainList from './SeriesMainList'
import { cover } from 'series'

type SeriesMainProps = {
  SeriesData:cover;
}

function SeriesMain({SeriesData}:SeriesMainProps) {
  const Info = SeriesData
  return (
    <MainContainer>
      <MainHeader>
        <BtnContainer>
          <SortBtn>
            1화보기
          </SortBtn>
          <SortBtn>
            최신부터
          </SortBtn>
        </BtnContainer>
        <div>14개의 스토리</div>
      </MainHeader>
      <SeriesMainList Info={Info} />
    </MainContainer>
  )
}

const MainContainer = styled.div`
    display: flex;
    flex-direction: column;
`
const MainHeader = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
`

const BtnContainer = styled.div`
  display: flex;
  flex-direction: row;
`
const SortBtn = styled.div`
  
`

export default SeriesMain;
