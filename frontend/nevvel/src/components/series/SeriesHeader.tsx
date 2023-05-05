import React from 'react'
import styled from 'styled-components'
import { cover } from 'series'

type SeriesHeaderProps = {
    SeriesData:cover
}

function SeriesHeader({SeriesData}:SeriesHeaderProps) {
    const Info = SeriesData
    return (
        <HeaderContainer>
            <HeaderTitle>시리즈 상세보기 </HeaderTitle>
            <SeriesInfo>
                <SeriesEx>{Info.thumbnail}</SeriesEx>
                <SeriesEx>
                    <div>{Info.title}</div>
                    <div>{Info.writter.nickname}</div>
                    <div>{Info.description}</div>
                    <Tag>{Info.genre}</Tag>
                    <SeriesBtnContainer>
                        <SeriesBtn>첫화보기</SeriesBtn>
                        <SeriesBtn>선택구매</SeriesBtn>
                    </SeriesBtnContainer>
                    <div>{Info.views}</div>
                    <div>{Info.likes}</div>
                </SeriesEx>
            </SeriesInfo>

        </HeaderContainer>
    )
}

const HeaderContainer = styled.div`
    display: flex;
    flex-direction: column;
    height: 40%;
    width: 100%;
    margin-bottom: 10%;

`

const HeaderTitle = styled.div`
    display: flex;
    justify-content: center;
    text-align: center;
    align-items: center;
    height: 20%;
    border-bottom: 1px solid ${({ theme })=> theme.color.text3} ;
`
const SeriesInfo = styled.div`
    display: flex;
    width: 100%;
    flex-direction: row;
    margin-top: 1rem;
    padding-bottom: 2rem;
    border-bottom: 1px solid ${({ theme })=> theme.color.text3} ;

`
const SeriesEx = styled.div`
    flex-direction: column;
    width: 50%;
    div{
        padding: 0.5rem;
    }
`
const Tag = styled.div``

const SeriesBtnContainer = styled.div`
    
`
const SeriesBtn = styled.button`
    
`

export default SeriesHeader
