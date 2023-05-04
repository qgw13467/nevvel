import React, { useEffect, useState } from "react";
import styled from "styled-components";
import DummyTagData from './DummyTagData.json'

function TagSearchBar(){

  // axios로 태그 데이터 받아오기

  // axios후 태그 리스트 만들기
  // const [tagLIst, ketTagLIst] = useState<string>([])
  useEffect(() => {
    // DummyTagData.content[]
  },[])

  // 검색어 받기
  const [keyword, setKeyword] = useState<string>("")

  const onChangeKeyword = (e: React.FormEvent<HTMLInputElement>) => {
    setKeyword(e.currentTarget.value.trim())
  }

  // 검색어 입력시, 태그 데이터와 비교
  useEffect(() => {
    console.log(DummyTagData.content["tagName"])
  },[keyword])

  return(
    <AssetInfoInputDiv>
      <AssetInfoInput1
        placeholder="에셋 태그를 입력하고 선택해주세요."
        onChange={onChangeKeyword}
       />
    </AssetInfoInputDiv>
  )
}

export default TagSearchBar

const AssetInfoInputDiv = styled.div`
  width: 15rem;
  height: 2.5rem;
`

const AssetInfoInput1 = styled.input`
  width: 15rem;
  height: 2.5rem;
  border: 0.15rem solid #4D4D4D;
  border-radius: 0.6rem;
`