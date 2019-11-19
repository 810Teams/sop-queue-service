**API Endpoints : GET AND POST COMMENT**

POST /Comment
ส่ง comment ขึ้นไปบน service
{
        "customer": "String", #customername
        "name": "String", #shopname
        "review": "String"
}
GET /Comment
ทำการดึง Comment จาก Service 

GET /Comment/ชื่อร้าน
ทำการดึง Comment จากชื่อร้าน บน service 

Use to verify token 
-------------------
*** How to use ***
1.Attach token with header `Bearer tokenxxx`
2.Send get request
3.Wait for responding from server
-------------------

