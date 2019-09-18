Assumptions –
1)	Limited customer orders are accepted based on Drone delivery capacity for each day
2)	Customer Orders file would be updated with new orders realtime /near realtime
with flag
3)	Undelivered customer orders would be carry forwarded to nextday customer orders file after cutoff time 10 PM on that day
4)	NPS calculation will be done on delivered orders
5)	Temp file/temp table would be created to manage the status of customer orders delivery status, and it will be in the sorted order based on distance b/w warehouse and customer location
6)	To maximize net promoter score (NPS) nearest location order will be delivered first
7)	Customer Order file would have only E,W,N,S indicators for directions
8)	Orders can be delivered in any sequence
9)	Considering Neutral list also in promotors list while calculating NPS
10)	Drone will carry only one customer order any given time
11)	Drone will return back to warehouse after each customer delivery
12)	NPS calculation – roundup the hours ex – 1hr 31min delivery time to 2Hrs
13)	Good weather conditions, no impediments for delivery
