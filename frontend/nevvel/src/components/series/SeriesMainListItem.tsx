import React, { useState } from 'react'
import { episode } from 'series'
import styled from 'styled-components'

type SeriesMainListItemProps = {
    episode: episode
}

function SeriesMainListItem({ episode }: SeriesMainListItemProps) {
    const [purchased, setPurchased] = useState(episode.isPurchased)
    const [IsRead, setIsRead] = useState(episode.isRead)
    return (
        <ItemContainer IsRead={IsRead}>
            <div>{episode.title}</div>
            <ItemBottom>
                <div>{episode.views}</div>
                <div>{episode.uploadedDateTime}</div>
            </ItemBottom>
        </ItemContainer>
    )
}


const ItemContainer = styled.div< { IsRead: boolean } >`
    color: ${(props) => (props.IsRead ? ({ theme }) => theme.color.text3 : ({ theme }) => theme.color.text1)};
    height: 10vh;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    padding-top: 1rem;
    /* padding-bottom: 0.5rem; */
    border-bottom: 1px solid ${({theme})=>theme.color.text3};
`
const ItemBottom = styled.div`
    display: flex;
    justify-content: flex-end;
    text-align: end;
    align-items: flex-end;

`

export default SeriesMainListItem
