--SELECT (POINT(12.34, 0.03)<->POINT(latitude, longitude)) as distance FROM resto

select (POINT(44.837944,-0.5748138)<->POINT(latitude, longitude)) as distance, r.id , r.* from resto r where (POINT(44.837944,-0.5748138)<->POINT(latitude, longitude))*6367445*pi()/180<500 order by (POINT(44.837944,-0.5745138)<->POINT(latitude, longitude))
select (POINT(44.837944,-0.5745138)<->POINT(latitude, longitude)) as distance, r.id , r.raison_sociale from resto r where (POINT(44.837944,-0.5745138)<->POINT(latitude, longitude))*6367445*pi()/180<500 order by (POINT(44.837944,-0.5745138)<->POINT(latitude, longitude))
