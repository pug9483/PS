# ANIMAL_INS: 동물 보호소에 들어온 동물의 정보를 담은 테이블
# ANIMAL_OUTS: 테이블은 동물 보호소에서 입양 보낸 동물의 정보를 담은 테이블

# 입양을 못 간 동물 중, 가장 오래 보호소에 있었던 동물 3마리의 이름과 보호 시작일을 조회하는 SQL문을 작성해주세요. 보호 시작일 순으로 조회
SELECT ANIMAL_INS.NAME, ANIMAL_INS.DATETIME
FROM ANIMAL_INS LEFT JOIN ANIMAL_OUTS
ON ANIMAL_INS.ANIMAL_ID = ANIMAL_OUTS.ANIMAL_ID
WHERE ANIMAL_OUTS.ANIMAL_ID IS NULL
ORDER BY ANIMAL_INS.DATETIME
LIMIT 3;
