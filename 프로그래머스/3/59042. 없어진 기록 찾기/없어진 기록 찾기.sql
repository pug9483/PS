# ANIMAL_INS: 동물 보호소에 들어온 동물의 정보
# ANIMAL_OUTS: 입양 보낸 동물의 정보
# ANIMAL_OUTS 테이블의 ANIMAL_ID는 ANIMAL_INS의 ANIMAL_ID의 외래 키

# 입양을 간 기록은 있는데, 보호소에 들어온 기록이 없는 동물의 ID와 이름을 ID 순으로 조회하는 SQL문을 작성해주세요.

select ANIMAL_OUTS.ANIMAL_ID, ANIMAL_OUTS.NAME
from ANIMAL_INS right join ANIMAL_OUTS
on ANIMAL_INS.ANIMAL_ID =  ANIMAL_OUTS.ANIMAL_ID
where ANIMAL_INS.ANIMAL_ID is null