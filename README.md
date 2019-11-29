# Reservation Service

Reservation Service project of Service-Oriented Programming Class, Information Technology, King Mongkut's Institute of Technolgoy Ladkrabang.

<img src="https://www.lavi.com/wp-content/uploads/2013/07/Why-We-Wont-Wait-blog.jpg" width="100%">

## What is Reservation Service ?

Reservation Service is a service that would grant you an ability to queue or make a reservation for you on a specific shop. Queue ahead, then receive goods or service on time. No need to waste time waiting at the spot.

## Why Reservation Service ?

Have you ever went to your favorite restaurant, barber shop or beauty salon and wait so long? When you thought 20 minutes of time is enough, but it results in 2 hours, which mostly is wasted on waiting. What if you can just queue or make a reservation ahead, then receive goods or service on time. Save more time to do other things than waiting. In addition, there is no need worrying in the goods out of stock.

## Features

### Sign Up and Login

Before using our service, an account is needed. Once you have created your account, you will be able to freely use the service. Users can sign up as either a customer or a shop owner.

### View Shop Information

Customers can view shop information before making a queue or reservation.

### Manage Own Queues and Reservations

Main feature of the service. Customers can make, view and cancel queues and reservations. Starting by choosing the shop, select product or service, choose the time, fill other needed information and done! If you think you have made any mistakes, you can cancel the queue any time before the queue or reservation is due, or cancel before a certain duration according to the shop policy.

Please be aware that if the queue or reservation is falsely made, which means no products or services are actually served accoring to the queue or reservation may result in a penalty.

### Set Up Your Shop

As for shop owners, managers and staffs, they can set up the shop in our service for customers to make queues and reservations. Setting up a shop need a following information: shop name, shop type, shop summary, shop opening date & time and shop image. More advanced information and list of products or services can be added later.

### Manage Product or Service List

Shops are not able to run if there is no product or service. Shop owners, managers and staffs are able to manage this list, including add, view, update and delete items. All of them are basic operations of list management.

### Manage Queues and Reservations of Customers

After the shop is fully set up, customers can now use the Reservation Service of your shop. After a customer chose a certain product or service to make a queue or reservation, you will be able to see the one customer made. The rest is up to you how you would like to manage these queues and reservations.

### Comment

Customers can comment on a certain shop, basically a review.

## Conceptual Diagram

![](readme/img/design_diagram.png)

## Service API End-Points

- [Authentication Service](README-auth.md)
- [User Management Service](README-user.md)
- [Reservation Service](README-reservation.md)
- [Product Service](README-product.md)
- [Shop Service](README-shop.md)
- [Comment Service](README-comment.md)

## Team

|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t31.0-8/23000276_1792200687491009_9032554056712951719_o.jpg?_nc_cat=108&_nc_eui2=AeG8WRtBc1DM4Ns1EEFC6Sjo8l-GNCeebuxhszVslXAmYg6XiJF-Im4RMQy66_J9TleGz4mP1drUPNXLucefKlL7V4ffzWpv4cF1b90MeF39bA&_nc_oc=AQnn-TLA4gfgVofq0tvco1pupCPDTO2CdEqxb7xN8Z89cNGeLC-kQmFzmTjqX0hbe7WVAOnX8MDiyRpJoDuDqj1e&_nc_ht=scontent.fbkk5-1.fna&oh=6b73bcdf5d459800aaddfd5730c3c8f0&oe=5E39FA68" width="150px" height="150px">|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t1.0-9/56591969_597023884136233_4676427440619257856_o.jpg?_nc_cat=105&_nc_eui2=AeH4uV_uCisqGuiKBGkBfHa-KA9orCRr6T6c11Xmum6seA2m7GkKy6tdvAwdPTZIwLR0442yKNjAs9OqPH4OjI4ihJ9Kpc7fYFawpIkzDWKLrQ&_nc_oc=AQkl5ydfM5PP9mphiOWZaMLi4-k5y9C87HEQFjHqMoqf_XvVqn9AC6xyqSsTtMBJ9YhU6gzPX0gXujoj9ddngkkI&_nc_ht=scontent.fbkk5-1.fna&oh=5f9a4308ee8fc9adb06b4df8ea5423ec&oe=5E07F911" width="150px" height="150px">|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t31.0-8/22791592_1464805656947913_6318736334116888243_o.jpg?_nc_cat=101&_nc_eui2=AeHBMNZFm_QYGGZ3a-6UySh4FOi77Gc_PyRE1l0RU9xmdD9ixmTJxxvUDFkVAvfdhtZe2hKQncgVGfcJWR34a6RjoG55kDkunAA8yT-m6hHQUw&_nc_oc=AQnME5884mXPd4OVsz4N8anINKxaxF48LHB1q1-wCm1zoUHwtzIDAkdRgeg2wf6-vHmbUSZF94edFHYvJC8Az90V&_nc_ht=scontent.fbkk5-1.fna&oh=8013b0f7bd556df2b9e0ecdbf4990888&oe=5E3C8F40" width="150px" height="150px">|<img src="https://scontent.fbkk5-1.fna.fbcdn.net/v/t1.0-9/22815523_1233968906709298_3388605270727140154_n.jpg?_nc_cat=100&_nc_eui2=AeFHU4f6s6RxSla9H6878B_uaa3UgBPfaQF98IqKp3uGizWW8E7KVlRTOSr7sgZk2snVB_lIhggHqyBORfCBFE98AarWSb9EITM02ajZAHrsIg&_nc_oc=AQlNUpp5QCK7LGagyKG1bDahZUAebFo7K6gbwvGWVbCOFLmX7ybrTyKYb2Rgqr7SOD6resK6d-_10q1sA8hmcUYm&_nc_ht=scontent.fbkk5-1.fna&oh=f2d1cff1e49ec041b25e9163f296937e&oe=5E0876E0" width="150px" height="150px">|
|:---:|:---:|:---:|:---:|
|[810Teams](https://github.com/810Teams)|[Punmanat](https://github.com/Punmanat)|[Narongded](https://github.com/Narongded)|[prachyaprapawat](https://github.com/prachyaprapawat)|
|Teerapat<br>Kraisrisirikul|Punmanat<br>Nantasanti|Narongded<br>Pinprechachai|Prachya<br>Prapawat|
|60070183|60070053|60070046|60070023|

[![forthebadge](https://forthebadge.com/images/badges/60-percent-of-the-time-works-every-time.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/made-with-javascript.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/powered-by-responsibility.svg)](https://forthebadge.com)
