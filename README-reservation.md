# API Endpoints : Reservation Service

### Create New Reservation

`POST /reservation/new`

- Authentication required
- Customer token only

```json
{
    "items": [
        {
            "itemId": "String",
            "amount": "Integer"
        }
    ],
    "timePeriod": {
        "startDateTime": "yyyy-MM-dd hh:mm",
        "endDateTime": "yyyy-MM-dd hh:mm"
    },
    "message": "String"
}
```

### View All Reservation

`GET /reservation/all`

- Only for testing and development

### View Single Reservation

`POST /reservation/id/{id}`

- Authentication required
- Customer or shop owner of a certain reservation

### View Own Reservation

`POST /reservation/me`

- Authentication required
- Customer token: Show all customer's reservations
- Shop owner token: Show all shop's received reservations

### Cancel Reservation

`POST /reservation/id/{id}/cancel`

- Authentication required
- Customer token: Can only cancel before reservation start date time
- Shop owner token: Cancel *before* reservation end date time will change reservation's status to CANCELLED_BY_SHOP
- Shop owner token: Cancel *after* reservation end date time will change reservation's status to MISSED

### Check Reservation as Success


## Group Members
|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t31.0-8/23000276_1792200687491009_9032554056712951719_o.jpg?_nc_cat=108&_nc_eui2=AeG8WRtBc1DM4Ns1EEFC6Sjo8l-GNCeebuxhszVslXAmYg6XiJF-Im4RMQy66_J9TleGz4mP1drUPNXLucefKlL7V4ffzWpv4cF1b90MeF39bA&_nc_oc=AQnn-TLA4gfgVofq0tvco1pupCPDTO2CdEqxb7xN8Z89cNGeLC-kQmFzmTjqX0hbe7WVAOnX8MDiyRpJoDuDqj1e&_nc_ht=scontent.fbkk5-1.fna&oh=6b73bcdf5d459800aaddfd5730c3c8f0&oe=5E39FA68" width="150px" height="150px">|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t1.0-9/56591969_597023884136233_4676427440619257856_o.jpg?_nc_cat=105&_nc_eui2=AeH4uV_uCisqGuiKBGkBfHa-KA9orCRr6T6c11Xmum6seA2m7GkKy6tdvAwdPTZIwLR0442yKNjAs9OqPH4OjI4ihJ9Kpc7fYFawpIkzDWKLrQ&_nc_oc=AQkl5ydfM5PP9mphiOWZaMLi4-k5y9C87HEQFjHqMoqf_XvVqn9AC6xyqSsTtMBJ9YhU6gzPX0gXujoj9ddngkkI&_nc_ht=scontent.fbkk5-1.fna&oh=5f9a4308ee8fc9adb06b4df8ea5423ec&oe=5E07F911" width="150px" height="150px">|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t31.0-8/22791592_1464805656947913_6318736334116888243_o.jpg?_nc_cat=101&_nc_eui2=AeHBMNZFm_QYGGZ3a-6UySh4FOi77Gc_PyRE1l0RU9xmdD9ixmTJxxvUDFkVAvfdhtZe2hKQncgVGfcJWR34a6RjoG55kDkunAA8yT-m6hHQUw&_nc_oc=AQnME5884mXPd4OVsz4N8anINKxaxF48LHB1q1-wCm1zoUHwtzIDAkdRgeg2wf6-vHmbUSZF94edFHYvJC8Az90V&_nc_ht=scontent.fbkk5-1.fna&oh=8013b0f7bd556df2b9e0ecdbf4990888&oe=5E3C8F40" width="150px" height="150px">|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t1.0-9/22815523_1233968906709298_3388605270727140154_n.jpg?_nc_cat=100&_nc_eui2=AeFHU4f6s6RxSla9H6878B_uaa3UgBPfaQF98IqKp3uGizWW8E7KVlRTOSr7sgZk2snVB_lIhggHqyBORfCBFE98AarWSb9EITM02ajZAHrsIg&_nc_oc=AQlNUpp5QCK7LGagyKG1bDahZUAebFo7K6gbwvGWVbCOFLmX7ybrTyKYb2Rgqr7SOD6resK6d-_10q1sA8hmcUYm&_nc_ht=scontent.fbkk5-1.fna&oh=f2d1cff1e49ec041b25e9163f296937e&oe=5E0876E0" width="150px" height="150px">|
|:---:|:---:|:---:|:---:|
|[810Teams](https://github.com/810Teams)|[Punmanat](https://github.com/Punmanat)|[Narongded](https://github.com/Narongded)|[prachyaprapawat](https://github.com/prachyaprapawat)|
|Teerapat<br>Kraisrisirikul|Punmanat<br>Nantasanti|Narongded<br>Pinprechachai|Prachya<br>Prapawat|
|60070183|60070053|60070046|60070023|

[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
