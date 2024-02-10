select 
    C.CAR_ID,
    C.CAR_TYPE,
    round(C.DAILY_FEE * 30 * (100 - P.DISCOUNT_RATE) / 100) as fee
from
    CAR_RENTAL_COMPANY_CAR C
    join CAR_RENTAL_COMPANY_RENTAL_HISTORY H on C.CAR_ID = H.CAR_ID
    join CAR_RENTAL_COMPANY_DISCOUNT_PLAN P on C.CAR_TYPE = P.CAR_TYPE
where
    C.CAR_ID not in(
        select CAR_ID
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where END_DATE >= '2022-11-01' and START_DATE <= '2022-12-01'
    )
    and
    P.duration_type like '30%'
group by C.CAR_ID	
having C.CAR_TYPE in ('세단', 'SUV') and (fee >= 500000 and fee < 2000000)
order by fee desc, car_type, car_id desc;

        