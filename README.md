

POST
/api/BuyProduct

{
    "user_id":1,
    "products":[
        {
            "product_id": 1,
            "quantity":1
        },
        {
            "product_id": 1,
            "quantity":1    
        }
    ]

}

POST
/api/Users/New
{
    "name": "Sarahi",
    "lastName": "Jose2",
    "bio": "bio2",
    "email": "jiji@hotmail.com",
    "areaOfInterest": "music"
}

POST
/api/Products/New
{
        "name": "vaso3",
        "price": 203,
        "image": null,
        "description": "cristal3",
        "totalProductsInventory": 5,
        "status": true
}

POST
/api/WishList/AddProduct
{
        "user_id" : 2,
        "product_id": [2,3]
}

DELETE
/api/WishList/DeleteProduct
{
        "user_id" : 2,
        "product_id": [2,3]
}

DELETE
/api/WishList/DeleteWishList
{
        "user_id" : 2
}

PUT
/api/Products/Update/{PRODUCT_ID}
{
        "totalProductsInventory": 5
}
