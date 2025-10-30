SELECT B.FLAVOR
FROM (
    select first_half.flavor, sum(first_half.total_order + july.total_order) as a
    from first_half join july
    where first_half.FLAVOR = july.FLAVOR
    group by first_half.FLAVOR
    order by a desc
) B
limit 3